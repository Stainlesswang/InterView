##SQL进阶教程 (MICK著)笔记

###第一章:神奇的SQL
---
1. **CASE表达式**

	case表达式的语法简介:
	
	*简单case表达式:*
	
	```sql
	CASE sex
		WHRN '1' THEN '男'
		WHRN '2' THEN '女'
	ELSE '其他' END
	```
	
	*搜索case表达式:*
	
	```sql
	CASE WHRN '1' THEN '男'
		  WHRN '2' THEN '女'
	ELSE '其他' END
	```