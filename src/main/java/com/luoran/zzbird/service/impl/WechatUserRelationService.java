package com.luoran.zzbird.service.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.luoran.zzbird.entity.biz.TWechatUser;
import com.luoran.zzbird.entity.biz.TXcxUser;
import com.luoran.zzbird.service.ITWechatUserService;
import com.luoran.zzbird.service.ITXcxUserService;
import com.luoran.zzbird.service.IWechatUserRelationService;

/**
 * 
 * @author lifetime
 *
 */
@Service
public class WechatUserRelationService implements IWechatUserRelationService {

	private final static Logger logger = LoggerFactory.getLogger(WechatUserRelationService.class);

	private ExecutorService executorService = Executors.newSingleThreadExecutor();

	/**
	 * 缓存img url与图片对象的映射
	 */
	private WeakHashMap<String, WeakReference<BufferedImage>> ImgCache = new WeakHashMap<>();

	/**
	 * 误差因素值
	 */
	static int diff_factor = 8;

	@Autowired
	private ITWechatUserService wechatService;

	@Autowired
	private ITXcxUserService xcxService;

	@Override
	public void notifyAddWxUser(String wxOpenId) {
		if (StringUtils.isEmpty(wxOpenId)) {
			return;
		}
		executorService.execute(new Runnable() {
			public void run() {
				TWechatUser user = wechatService.queryGzhUserByOpenId(wxOpenId);
				if (user == null) {
					return;
				}
				String imgUrl = user.getHeadimgUrl();
				BufferedImage wxImgObj = getImgObject(imgUrl);
				if (wxImgObj == null) {
					return;
				}
				List<TXcxUser> likeList = findLikeUser(user);
				float maxLikeVal = 0F;
				String maxXcxOpenId = null;
				for (TXcxUser oUser : likeList) {
					BufferedImage uimg = getImgObject(oUser.getAvatarUrl());
					float likeVal = getImageLikeVal(wxImgObj, uimg);
					if (likeVal > 90) {
						// 如果相似度高于90%，则认为是同一个用户。如果存在多个，则取相似度最高的那个
						if (likeVal > maxLikeVal) {
							maxXcxOpenId = oUser.getOpenId();
							maxLikeVal = likeVal;
						}
					}
				}

				if (!StringUtils.isEmpty(maxXcxOpenId)) {
					relation(maxXcxOpenId, wxOpenId);
				}
			}
		});
	}

	/**
	 * 关联小程序与公众号的微信用户信息
	 * 
	 * @param xcxOpenId
	 * @param wxOpenId
	 */
	public void relation(String xcxOpenId, String wxOpenId) {

	}

	/**
	 * 获取图片相似度
	 * 
	 * @param img1
	 * @param img2
	 * @return
	 */
	protected float getImageLikeVal(BufferedImage img1, BufferedImage img2) {
		int w = img1.getWidth();
		int h = img1.getHeight();
		int w2 = img2.getWidth();
		int h2 = img2.getHeight();
		if (w != w2 || h != h2) {
			return 0F;
		}
		int total = 0;
		int diff = 0;
		for (int i = img1.getMinX(); i < w; i++) {
			for (int j = img1.getMinY(); j < h; j++) {
				int rgb = img1.getRGB(i, j);
				int r = rgb >> 16 & 0xff;
				int g = rgb >> 8 & 0xff;
				int b = rgb & 0xff;

				int rgb2 = img2.getRGB(i, j);
				int r2 = rgb2 >> 16 & 0xff;
				int g2 = rgb2 >> 8 & 0xff;
				int b2 = rgb2 & 0xff;
				total++;
				if (Math.abs(r - r2) > diff_factor || Math.abs(g - g2) > diff_factor || Math.abs(b - b2) > diff_factor) {
					diff++;
				}
			}
		}
		float likeVal = 100F * (total - diff) / total;
		return likeVal;
	}

	/**
	 * 
	 * @param imgUrl
	 * @return
	 */
	protected BufferedImage getImgObject(String imgUrl) {
		if (StringUtils.isEmpty(imgUrl)) {
			return null;
		}
		WeakReference<BufferedImage> existsImg = ImgCache.get(imgUrl);
		BufferedImage image = null;
		if (existsImg != null && existsImg.get() != null) {
			image = existsImg.get();
		} else {
			try {
				image = ImageIO.read(new URL(imgUrl));
				ImgCache.put(imgUrl, new WeakReference<BufferedImage>(image));
			} catch (MalformedURLException e) {
				logger.error(e.getMessage(), e.getCause());
			} catch (IOException e) {
				logger.error(e.getMessage(), e.getCause());
			}
		}
		return image;
	}

	/**
	 * 根据小程序的用户查找类似的微信用户(基于昵称匹配)
	 * 
	 * @param user
	 * @return
	 */
	protected List<TWechatUser> findLikeUser(TXcxUser user) {
		return wechatService.queryGzhUserByNickName(user.getNickName());
	}

	/**
	 * 根据微信用户查找类似的小程序用户(基于昵称匹配)
	 * 
	 * @param user
	 * @return
	 */
	protected List<TXcxUser> findLikeUser(TWechatUser user) {
		return xcxService.queryXcxUserByNickName(user.getNickName());
	}

	@Override
	public void notifyAddXcxUser(String xcxOpenId) {
		if (StringUtils.isEmpty(xcxOpenId)) {
			return;
		}
		executorService.execute(new Runnable() {
			public void run() {
				TXcxUser user = xcxService.queryXcxUserByOpenId(xcxOpenId);
				if (user == null) {
					return;
				}
				String imgUrl = user.getAvatarUrl();
				BufferedImage wxImgObj = getImgObject(imgUrl);
				if (wxImgObj == null) {
					return;
				}
				List<TWechatUser> likeList = findLikeUser(user);
				float maxLikeVal = 0F;
				String maxWechatOpenId = null;
				for (TWechatUser oUser : likeList) {
					BufferedImage uimg = getImgObject(oUser.getHeadimgUrl());
					float likeVal = getImageLikeVal(wxImgObj, uimg);
					if (likeVal > 90) {
						// 如果相似度高于90%，则认为是同一个用户。如果存在多个，则取相似度最高的那个
						if (likeVal > maxLikeVal) {
							maxWechatOpenId = oUser.getOpenId();
							maxLikeVal = likeVal;
						}
					}
				}

				if (!StringUtils.isEmpty(maxWechatOpenId)) {
					relation(xcxOpenId, maxWechatOpenId);
				}
			}
		});
	}

}
