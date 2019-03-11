queryXcxUserByOpenId
===
* 根据openId查询小程序用户
	
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

	
queryXcxUserByNickName
===
* 根据用户名查询小程序用户

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
	
queryNewOrOldUser
===
* 根据sessionKey查询是否是新老用户

	SELECT
		r.id AS xcxUserRoleId,
		r.company_id AS companyId,
		c1.company_name AS companyName,
		r.role_val AS roleVal,
		r.role_name AS roleName,
		r.role_headimg AS headImg,
		r.current_active AS currentActive
		FROM
		t_xcx_user_role AS r ,
		t_xcx_user AS u ,
		t_company AS c1
		WHERE
		u.id = r.xcx_user_id AND
		MD5(u.open_id) = #sessionKey# AND
		c1.id = r.company_id
