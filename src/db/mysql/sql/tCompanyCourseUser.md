* 根据传入的Boos角色Id 查询学生
queryComUserByBoosRole
===
select 
	@pageTag(){
		t_user.id user_roleid,
		t_user.role_name user_name,
		t_user.role_headimg user_img,
		t_user.role_val user_roleval,
		t_user.company_id user_company_id,
		tcom.company_name user_company_name
	@}
from t_xcx_user_role comUser
left join t_xcx_user_role t_user ON comUser.company_id = t_user.company_id
inner join t_company tcom on t_user.company_id = tcom.id
	@where(){
		comUser.isdelete = 0
	and t_user.isdelete = 0
	and comUser.role_val = 10
	and t_user.role_val = #roleType#
	and comUser.id = #roleId#
	@} 
	
	

queryCourseByUserRoleId
===
* 根据传入的学生角色Id 学生在本公司下的课程
select
	tcc.id course_id,
	tcc.course_name course_name
from t_company_course_user tccu
INNER JOIN t_company_course tcc on tccu.company_course_id = tcc.id
where tccu.xcx_user_role_id = #roleId#
ORDER BY tcc.id



queryCourseUserByCourseId
===
* 根据传入的课程id和角色身份来查询这个课程下的学生或者老师
	SELECT
		tx.role_name name,
		tx.role_headimg headImg
		FROM
		t_company_course_user AS tc ,
		t_xcx_user_role AS tx
		WHERE
		tc.xcx_user_role_id = tx.id AND
		tc.company_course_id = #courseId# AND
		tx.role_val = #roleVal#
		ORDER BY
		tc.add_time DESC	

queryCourseByUserList
===
* 查询用户所属的公司对应角色的课程

	SELECT
	@pageTag(){
		c.id,
		c.company_id,
		c.course_name,
		c.start_time,
		c.end_time,
		c.person_number,
		c.course_img,
		c1.company_name AS companyName
	@}
	FROM
	t_company_course_user AS cu ,
	t_company_course AS c ,
	t_xcx_user_role AS ur ,
	t_xcx_user AS u,
	t_company AS c1
	WHERE
	cu.company_course_id = c.id AND
	cu.xcx_user_role_id = ur.id AND
	c.company_id = #companyId# AND
	u.open_id = #openId# AND
	u.id = ur.xcx_user_id AND
	ur.role_val = #roleVal# AND
	c.company_id = c1.id
	ORDER BY  cu.add_time DESC

queryUserCourseCount
===
	 * 查询用户下的课程总数，仅限老师和学生
	 select 
		count(1)
	from t_company_course_user tccu
	inner join t_xcx_user_role txur on tccu.xcx_user_role_id = txur.id
	where 
		txur.isdelete = 0
		and tccu.xcx_user_role_id = #roleId#
		
		
		
queryTeaCourseStuCount
===
		* 查询老师的学生人数
		SELECT
			COUNT(1)
		FROM
			(
				SELECT
					COUNT(txurStu.id) stu
				FROM
					t_company_course_user tccuTea
				LEFT JOIN t_company_course_user tccuStu ON tccuStu.company_course_id = tccuTea.company_course_id
				INNER JOIN t_xcx_user_role txurTea ON tccuTea.xcx_user_role_id = txurTea.id
				INNER JOIN t_xcx_user_role txurStu ON tccuStu.xcx_user_role_id = txurStu.id
				WHERE
					txurTea.isdelete = 0
				AND txurStu.isdelete = 0
				AND txurTea.id = #roleId#
				AND txurStu.role_val = 30
				GROUP BY
					txurStu.id
			) stu
			
			
			
queryTeaCourseStu
===
		* 查询老师下的学生
		select 
			@pageTag(){
				stu.user_roleid,
				stu.user_name,
				stu.user_img,
				stu.user_company_name
			@}
		FROM(
				SELECT
				
					txurStu.id user_roleid,
					txurStu.role_name user_name,
					txurStu.role_headimg user_img,
					tc.company_name user_company_name
				FROM
					t_company_course_user tccuTea
				LEFT JOIN t_company_course_user tccuStu ON tccuStu.company_course_id = tccuTea.company_course_id
				INNER JOIN t_xcx_user_role txurTea ON tccuTea.xcx_user_role_id = txurTea.id
				INNER JOIN t_xcx_user_role txurStu ON tccuStu.xcx_user_role_id = txurStu.id
				INNER JOIN t_company tc on txurStu.company_id = tc.id 
				@where(){
					txurTea.isdelete = 0
				AND txurStu.isdelete = 0
				AND txurTea.id = #roleId#
				AND txurStu.role_val = 30
				GROUP BY
					txurStu.id
				@} 
			) stu