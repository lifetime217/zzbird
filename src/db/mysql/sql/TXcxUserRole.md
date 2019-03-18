queryCompanyUser
===
* 查询公司的用户 根据传入的参数来判断查询公司老师，还是公司学生
	
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
	and  role_val = #roleVal#

	
updateCurrentActiveByZero
===
* 修改正在使用的角色为0

	UPDATE t_xcx_user_role 
	SET current_active = 0 
	WHERE
	xcx_user_id = #xcxUserId# 
	AND current_active = 1 
	
updateActive
===
* 修改正在使用的橘色
	UPDATE t_xcx_user_role 
	SET current_active = 1
	WHERE
	id = #id# 
	
