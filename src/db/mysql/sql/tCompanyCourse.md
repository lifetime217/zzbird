queryCourseDetail
===
	*根据课程id查询课程的详情
	SELECT
		c.id,
		c.company_id,
		c.course_name,
		c.course_hour,
		c.age_range,
		c.start_time,
		c.end_time,
		c.person_number,
		c.course_desc,
		c.course_img,
		c1.company_name
		FROM
		t_company_course AS c ,
		t_company AS c1
		WHERE
		c.id =#courseId# AND
		c.company_id = c1.id

		
queryCourseCount
===
		* 查询公司下课程总数
		
		SELECT
			count(tcc.id)
		FROM
			t_company_course tcc
		WHERE
			tcc.company_id = #companyId#
			
updatePersonNumber
===
* 修改课程人数加一
	update t_company_course 
	set person_number=person_number+1 
	where id=#id#