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
	
queryAllRoleUser
===
* 根据sessionKey查询用户的角色 (如有currentActive就查单个)

	SELECT
		r.id,
		r.company_id,
		c1.company_name AS companyName,
		u.open_id AS openId,
		r.role_val,
		r.role_name,
		r.role_headimg,
		r.current_active
		FROM
		t_xcx_user_role AS r ,
		t_xcx_user AS u ,
		t_company AS c1
		WHERE
		u.id = r.xcx_user_id AND
		u.session_key = #sessionKey# AND
		c1.id = r.company_id
		@if(!isEmpty(currentActive)){
		 and r.current_active = #currentActive#
		@}
		
		
queryXcxUserRole
===
* 根据sessionKey、roleVal和companyId查询用户角色的id
	SELECT
	r.id,
	r.role_name
	FROM
	t_xcx_user_role AS r ,
	t_xcx_user AS u
	WHERE
	u.session_key =#sessionKey# AND
	r.role_val = #roleVal# AND
	r.company_id = #companyId# AND
	r.xcx_user_id = u.id



updateByUserId
===
   *根据小程序用户的Id 更新xcxOpenId字段
	UPDATE `zzbird_test`.`t_xcx_user` 
	SET
	`wx_open_id` = #xcxOpenId#
	WHERE
		`id` = #id#


