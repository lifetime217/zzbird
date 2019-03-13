package com.luoran.zzbird.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.hsr.geohash.GeoHash;
import ch.hsr.geohash.WGS84Point;

/**
 * 该类由第三方作者提供
 * 
 * @see https://www.linchaokun.cn/611.html?tdsourcetag=s_pcqq_aiomsg
 * @author Created by linchaokun on 2018/4/9.
 */
public class GeohashUtil {
	private static String format = "0.000000";
	private static final double EARTH_RADIUS = 6371000;// 赤道半径(单位m)
	private static int numberOfCharacters = 12;

	/**
	 * 根据经纬值得到Geohash字串
	 *
	 * @param lat
	 *            纬度值
	 * @param lon
	 *            经度值
	 * @return Geohash字串
	 */
	public static String encode(double lat, double lon) {
		return getGeoHash(lat, lon, numberOfCharacters).toBase32();

	}

	/**
	 * 根据经纬值得到Geohash字串
	 *
	 * @param lat
	 *            纬度值
	 * @param lon
	 *            经度值
	 * @param number
	 *            经度 1-12
	 * @return Geohash字串
	 */
	public static String encode(double lat, double lon, int number) {
		return getGeoHash(lat, lon, number).toBase32();

	}

	/**
	 * 获取整个九宫格的GeoHash的值
	 *
	 * @param lat
	 *            纬度值
	 * @param lon
	 *            经度值
	 * @return Geohash字串集合
	 */
	public static List<String> encodes(double lat, double lon) {
		List<String> hashs = new ArrayList<>();
		GeoHash[] adjacent = getGeoHash(lat, lon, numberOfCharacters).getAdjacent();// 获取整个九宫格的GeoHash的值

		for (GeoHash hash : adjacent) {
			hashs.add(hash.toBase32());
		}

		return hashs;
	}

	/**
	 * 获取整个九宫格的GeoHash的值
	 *
	 * @param lat
	 *            纬度值
	 * @param lon
	 *            经度值
	 * @param number
	 *            经度 1-12
	 * @return Geohash字串集合
	 */
	public static List<String> encodes(double lat, double lon, int number) {
		List<String> hashs = new ArrayList<>();
		GeoHash[] adjacent = getGeoHash(lat, lon, number).getAdjacent();// 获取整个九宫格的GeoHash的值

		for (GeoHash hash : adjacent) {
			hashs.add(hash.toBase32());
		}

		return hashs;
	}

	/**
	 * 根据GeoHash的值转换为经纬度
	 * 
	 * @param geohash
	 * @return
	 */
	public static double[] decode(String geohash) {

		GeoHash geoHash = GeoHash.fromGeohashString(geohash);
		WGS84Point point = geoHash.getPoint();
		double lat = point.getLatitude();
		double lon = point.getLongitude();

		DecimalFormat df = new DecimalFormat(format);

		return new double[] { Double.parseDouble(df.format(lat)), Double.parseDouble(df.format(lon)) };
	}

	/**
	 * 基于googleMap中的算法得到两经纬度之间的距离,计算精度与谷歌地图的距离精度差不多，相差范围在0.2米以下
	 * 
	 * @param lat1
	 *            第一点的精度
	 * @param lng1
	 *            第一点的纬度
	 * @param lat2
	 *            第二点的精度
	 * @param lng2
	 *            第二点的纬度
	 * @return 返回的距离，单位m
	 */

	public static double distance(double lat1, double lng1, double lat2, double lng2) {
		double x1 = Math.cos(lat1) * Math.cos(lng1);
		double y1 = Math.cos(lat1) * Math.sin(lng1);
		double z1 = Math.sin(lat1);
		double x2 = Math.cos(lat2) * Math.cos(lng2);
		double y2 = Math.cos(lat2) * Math.sin(lng2);
		double z2 = Math.sin(lat2);
		double lineDistance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
		double s = EARTH_RADIUS * Math.PI * 2 * Math.asin(0.5 * lineDistance) / 180;
		return Math.round(s * 10000) / 10000;
	}

	private static GeoHash getGeoHash(double lat, double lon, int number) {
		DecimalFormat df = new DecimalFormat(format);
		return GeoHash.withCharacterPrecision(Double.parseDouble(df.format(lat)), Double.parseDouble(df.format(lon)), number);
	}

	public static void main(String[] args) {
		// 116.402843,39.999375 鸟巢 wx4g8c9v
		// 116.3967,39.99932 水立方 wx4g89tk
		// 116.40382,39.918118 故宫 wx4g0ffe
		double lon1 = 121.4385790000;
		double lat1 = 31.3555530000;
		double lon2 = 121.4400601387;
		double lat2 = 31.3567703367;
		
		
		System.out.println("位置1：" + encode(lat1, lon1));
		System.out.println("位置1的周边：" + Arrays.toString(encodes(lat1, lon1,5).toArray()));
		
		System.out.println("位置2：" + encode(lat2, lon2));
//		double dist;
//		String geocode;
//		
//		String encode = encode(lat1,lon1,5);
//		System.out.println("geoHash " + encode + "");
//		
//		List<String> hashs = new ArrayList<>();
//
//		dist = distance(lat1, lon1, lat2, lon2);
//		System.out.println("两点相距1：" + dist + " 米");
//
//		hashs = encodes(lat1, lon1);
//	
//		System.out.println("当前位置编码：" + hashs.toString());
//		hashs = encodes(lat1, lon1,5);
//		System.out.println("当前位置编码：" + hashs.toString());
//		hashs = encodes(lat2, lat2);
//		System.out.println("远方位置编码：" + hashs.toString());
//		hashs = encodes(lat2, lon2,5);
//		System.out.println("远方位置编码：" + hashs.toString());
//
//		double[] decode = GeohashUtil.decode(encode(lat1, lon1));
//		System.out.println(decode[0] + "," + decode[1]);
		
		
		
		
		

	}
}