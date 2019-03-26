queryParentIndustry
===
* 查询父产业的标签
	SELECT
	i.id,
	i.`name`
	FROM
	t_industry AS i
	WHERE
	i.parent_id = 0




queryAllIndustry
===
* 查询子产业类型
	SELECT
	i.id,
	i.`name` iName,
	i.parent_id pId,
	p.`name` pName
	FROM
	t_industry AS i,
	t_industry AS p
	WHERE
	i.parent_id = p.id

