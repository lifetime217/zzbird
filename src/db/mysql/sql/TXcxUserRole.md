queryCompanyUser
===
* 查询公司的用户 根据传入的roleVal来判断查询公司老师，还是公司学生，或者企业创建者
	
	SELECT
	t_xcx_user_role.id,
	t_xcx_user_role.role_name,
	t_xcx_user_role.role_headimg,
	t_xcx_user_role.xcx_user_id,
	t_xcx_user_role.sign
	FROM
	t_xcx_user_role
	WHERE
	t_xcx_user_role.company_id=#companyId#
	AND  t_xcx_user_role.role_val = #roleVal#
	AND t_xcx_user_role.isdelete = 0
	@if(!isEmpty(userId)){
		 and t_xcx_user_role.xcx_user_id = #userId#
	@}

	
updateCurrentActiveByZero
===
* 修改正在使用的角色为0

	UPDATE t_xcx_user_role r,
	t_xcx_user u 
	SET r.current_active = 0
	WHERE
	r.xcx_user_id = u.id
	AND r.current_active = 1 
	and u.session_key = #sessionKey#
	
updateActive
===
* 修改正在使用的橘色
	UPDATE t_xcx_user_role 
	SET current_active = 1
	WHERE
	id = #id# 
	
queryCourseUserExist
===
* 用户收邀请进来查询该公司和该课程下是否是老师或者是学生
	SELECT
	r.*,c.company_course_id
	FROM
	t_xcx_user_role AS r ,
	t_xcx_user AS u ,
	t_company_course_user AS c
	WHERE
	r.xcx_user_id = u.id AND
	u.session_key = #sessionKey# AND
	r.id = c.xcx_user_role_id AND
	c.company_course_id = #courseId# 
	and  r.role_val in(20,30)
	