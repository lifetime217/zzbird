queryGzhUserByOpenId
===
* 根据openId查询公众号用户
	
	SELECT
	t_xcx_user.id,
	t_xcx_user.open_id,
	t_xcx_user.session_key,
	t_xcx_user.nick_name,
	t_xcx_user.avatar_url,
	t_xcx_user.avatar_url_md5,
	t_xcx_user.gender,
	t_xcx_user.country,
	t_xcx_user.province,
	t_xcx_user.city,
	t_xcx_user.`language`,
	t_xcx_user.add_time
	FROM
	t_xcx_user
	WHERE
	t_xcx_user.open_id =#openId#

	
queryGzhUserByNickName
===
* 根据用户名查询公众号用户

	SELECT
	t_xcx_user.id,
	t_xcx_user.open_id,
	t_xcx_user.session_key,
	t_xcx_user.nick_name,
	t_xcx_user.avatar_url,
	t_xcx_user.avatar_url_md5,
	t_xcx_user.gender,
	t_xcx_user.country,
	t_xcx_user.province,
	t_xcx_user.city,
	t_xcx_user.`language`,
	t_xcx_user.add_time
	FROM
	t_xcx_user
	WHERE
	t_xcx_user.nick_name =#nickName#