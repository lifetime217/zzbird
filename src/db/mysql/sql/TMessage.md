queryPageMessageList
===
		* 查询消息列表按接收人
		
		SELECT
			@pageTag(){
				tm.id,
				tm.title,
				tm.content,
				tm.`read`,
				tm.add_time,
				txurFrom.role_headimg send_img
			@}
		FROM
			t_message tm
		INNER JOIN t_xcx_user_role txurTo ON  txurTo.id = tm.to_user
		INNER JOIN t_xcx_user_role txurFrom ON txurFrom.id = tm.from_user
		@where(){
				tm.isdelete = 0
			AND txurFrom.isdelete = 0
			AND txurTo.isdelete = 0
			AND txurTo.id = #roleId#
			ORDER BY tm.add_time desc
		@}
		
		
queryMsgById
===
		* 根据id获取消息
		
		SELECT
			tm.id,
			tm.title,
			tm.content,
			tm.add_time,
			txurFrom.role_name
		FROM
			t_message tm
		INNER JOIN t_xcx_user_role txurTo ON  txurTo.id = tm.to_user
		INNER JOIN t_xcx_user_role txurFrom ON txurFrom.id = tm.from_user
		WHERE
			tm.isdelete = 0
		AND txurFrom.isdelete = 0
		AND txurTo.isdelete = 0
		AND tm.id = #roleId#
		
		
queryUnreadMessageCountByRoleId
===
  * 查询就收人的未读消息数量
		SELECT
			count(1) 
		FROM
			t_message AS tm 
		WHERE
			tm.`read` = 0 
			AND tm.to_user = #roleId#