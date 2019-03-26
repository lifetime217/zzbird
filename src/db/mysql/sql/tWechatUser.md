queryGzhUserByOpenId
===
* 根据openId查询公众号用户
	
	SELECT
	w.id,
	w.open_id,
	w.nick_name,
	w.sex,
	w.city,
	w.country,
	w.province,
	w.`language`,
	w.headimg_url,
	w.headimg_md5,
	w.subscribe_time,
	w.remark,
	w.groupid,
	w.tagid_list,
	w.subscribe_scene,
	w.qr_scene,
	w.qr_scene_str,
	w.subscribe 
FROM
	t_wechat_user AS w 
WHERE
	w.open_id = #openId#

	
queryGzhUserByNickName
===
* 根据用户名查询公众号用户

	SELECT
	w.id,
	w.open_id,
	w.nick_name,
	w.sex,
	w.city,
	w.country,
	w.province,
	w.`language`,
	w.headimg_url,
	w.headimg_md5,
	w.subscribe_time,
	w.remark,
	w.groupid,
	w.tagid_list,
	w.subscribe_scene,
	w.qr_scene,
	w.qr_scene_str,
	w.subscribe 
FROM
	t_wechat_user AS w 
WHERE
	w.nick_name = #nickName#