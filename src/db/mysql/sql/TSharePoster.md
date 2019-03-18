querySharePoster
===
* 根据海报模板id查询二维码背景图
	SELECT
	s.id,
	s.poster_id,
	s.type,
	s.color,
	s.font_size,
	s.is_center,
	s.distance_x,
	s.distance_y,
	s.img_url,
	s.img_width,
	s.img_height,
	s.variable_id,
	s.variable_name,
	s.createtime
	FROM
	t_share_poster AS s
	WHERE
	s.poster_id = #posterId#