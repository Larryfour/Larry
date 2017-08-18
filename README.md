# Sales CRM系统

Sales CRM系统相关协议

## 错误处理
如果有错误，统一返回400且带有下面的结构，发生错误时，应直接将detail显示出来。

400:
```json
{
    "type": "CrmException",
    "title": "Crm Exception",
    "detail": "course can not found. a21f43e0-4b2c-11e7-9c71-c81f66c50f7d3"
}
```

401， 403： 这两个特殊，401表示没有认证，403表示没有权限
```json
{
    "timestamp": "2017-07-11T07:18:31.712+0000",
    "status": 401,
    "error": "Unauthorized",
    "message": "Unable to authenticate with basic authentication",
    "path": "/sato/courses/a21f43e0-4b2c-11e7-9c71-c81f66c50f7d3"
}
```

## 客户

### 获取客户列表
REQ:  

GET /sales-crm/customers/page-get?page=0&size=2
```json
RESP:
{
    "links": [
        {
            "rel": "first",
            "href": "http://localhost:8080/sales-crm/customers/page-get?page=0&size=2"
        },
        {
            "rel": "self",
            "href": "http://localhost:8080/sales-crm/customers/page-get"
        },
        {
            "rel": "next",
            "href": "http://localhost:8080/sales-crm/customers/page-get?page=1&size=2"
        },
        {
            "rel": "last",
            "href": "http://localhost:8080/sales-crm/customers/page-get?page=7&size=2"
        }
    ],
    "content": [
        {
            "id": "6ca22016-83c7-11e7-964c-c81f66c50f7d",
            "createdDate": "2017-08-18T03:43:40Z",
            "createdBy": "sato:user:keycloak:sales",
            "lastModifiedDate": "2017-08-18T03:43:40Z",
            "lastModifiedBy": "sato:user:keycloak:sales",
            "version": 0,
            "contactName": "孙昊16",
            "contactMobile": "18612172126",
            "xuebaNo": null,
            "name": null,
            "mobile": null,
            "gender": null,
            "qq": null,
            "parents": null,
            "parentsMobile": null,
            "province": null,
            "city": null,
            "district": null,
            "school": null,
            "grade": null,
            "gradeNote": null,
            "teachingAterial": null,
            "teachingAterialNote": null,
            "scores": null,
            "fullMarks": null,
            "comment": null,
            "tutorialFlag": null,
            "learningProcess": null,
            "nextTest": null,
            "nextTestDate": null,
            "answerInterval": null,
            "ownedSalesID": 1,
            "ownedSalesName": "林依然",
            "ownedSalesUserName": "linyiran",
            "links": []
        },
        {
            "id": "5dc06ab7-83c4-11e7-964c-c81f66c50f7d",
            "createdDate": "2017-08-18T03:21:46Z",
            "createdBy": "sato:user:keycloak:sales",
            "lastModifiedDate": "2017-08-18T03:21:46Z",
            "lastModifiedBy": "sato:user:keycloak:sales",
            "version": 0,
            "contactName": "孙昊15",
            "contactMobile": "18612172125",
            "xuebaNo": null,
            "name": "孙昊15",
            "mobile": "18612172125",
            "gender": null,
            "qq": null,
            "parents": null,
            "parentsMobile": null,
            "province": null,
            "city": null,
            "district": null,
            "school": null,
            "grade": null,
            "gradeNote": null,
            "teachingAterial": null,
            "teachingAterialNote": null,
            "scores": null,
            "fullMarks": null,
            "comment": null,
            "tutorialFlag": null,
            "learningProcess": null,
            "nextTest": null,
            "nextTestDate": null,
            "answerInterval": null,
            "ownedSalesID": 1,
            "ownedSalesName": "林依然",
            "ownedSalesUserName": "linyiran",
            "links": []
        }
    ],
    "page": {
        "size": 2,
        "totalElements": 16,
        "totalPages": 8,
        "number": 0
    }
}
```
### 增加客户
REQ:  

POST /sales-crm/customers/create
```json
{
    "contactName": "孙昊（必填项）",
    "contactMobile": "18612172112（必填项）",
    "xuebaNo":"16517014",
    "name":"孙昊",
    "mobile":"18612172117",
    "gender":"Male",
    "qq":"120644874",
    "parents":"妈妈",
    "parentsMobile":"13630559553",
    "province":"吉林省",
    "city":"白山市",
    "district":"浑江区",
    "school":"白山市第九中学",
    "grade":"初二",
    "gradeNote":"",
    "teachingAterial":"初二",
    "teachingAterialNote":"",
    "scores":"66",
    "fullMarks":"100",
    "comment":"刚及格",
    "tutorialFlag":"false",
    "learningProcess":"无理数",
    "nextTest":"期中考试",
    "nextTestDate":"2017-10-10",
    "answerInterval":"14:00-15:00",
    "ownedSalesID":"1"
    "ownedSalesName":"销售主管名称"
    "ownedSalesUserName":"销售主管账户名称"
}
```

RESP:
```json
{   
    "id":"81d32b61-83bd-11e7-964c-c81f66c50f7d",
    "contactName": "孙昊",
    "contactMobile": "18612172112"
    "xuebaNo":"16517014",
    "name":"孙昊",
    "mobile":"18612172117",
    "gender":"Male",
    "qq":"120644874",
    "parents":"妈妈",
    "parentsMobile":"13630559553",
    "province":"吉林省",
    "city":"白山市",
    "district":"浑江区",
    "school":"白山市第九中学",
    "grade":"初二",
    "gradeNote":"",
    "teachingAterial":"初二",
    "teachingAterialNote":"",
    "scores":"66",
    "fullMarks":"100",
    "comment":"刚及格",
    "tutorialFlag":"false",
    "learningProcess":"无理数",
    "nextTest":"期中考试",
    "nextTestDate":"2017-10-10",
    "answerInterval":"14:00-15:00",
    "ownedSalesID":"1"
    "ownedSalesName":"销售主管名称"
    "ownedSalesUserName":"销售主管账户名称"
}
```

### 更新客户
REQ:  

PUT /sales-crm/customers/update
```json
{
	"id":"81d32b61-83bd-11e7-964c-c81f66c50f7d",
    "xuebaNo":"16517014",
    "name":"孙昊",
    "mobile":"18612172117",
    "gender":"Male",
    "qq":"120644874",
    "parents":"妈妈",
    "parentsMobile":"13630559553",
    "province":"吉林省",
    "city":"白山市",
    "district":"浑江区",
    "school":"白山市第九中学",
    "grade":"初二",
    "gradeNote":"",
    "teachingAterial":"初二",
    "teachingAterialNote":"",
    "scores":"66",
    "fullMarks":"100",
    "comment":"刚及格",
    "tutorialFlag":"false",
    "learningProcess":"无理数",
    "nextTest":"期中考试",
    "nextTestDate":"2017-10-10",
    "answerInterval":"14:00-15:00"
}
```

RESP:
```json
{
	"id":"81d32b61-83bd-11e7-964c-c81f66c50f7d",
    "xuebaNo":"16517014",
    "name":"孙昊",
    "mobile":"18612172117",
    "gender":"Male",
    "qq":"120644874",
    "parents":"妈妈",
    "parentsMobile":"13630559553",
    "province":"吉林省",
    "city":"白山市",
    "district":"浑江区",
    "school":"白山市第九中学",
    "grade":"初二",
    "gradeNote":"",
    "teachingAterial":"初二",
    "teachingAterialNote":"",
    "scores":"66",
    "fullMarks":"100",
    "comment":"刚及格",
    "tutorialFlag":"false",
    "learningProcess":"无理数",
    "nextTest":"期中考试",
    "nextTestDate":"2017-10-10",
    "answerInterval":"14:00-15:00"
}
```


### 根据客户id，获取客户详细信息
REQ:  

GET /sales-crm/customers/id/81d32b61-83bd-11e7-964c-c81f66c50f7d
```json
```

RESP:
```json
{
	"id": "81d32b61-83bd-11e7-964c-c81f66c50f7d",
	"createdDate": "2017-08-18T02:32:40Z",
	"createdBy": "sato:user:keycloak:sales",
	"lastModifiedDate": "2017-08-18T03:16:36Z",
	"lastModifiedBy": "sato:user:keycloak:sales",
	"version": 0,
	"contactName": "孙昊",
	"contactMobile": "18612172117",
	"xuebaNo": 16517014,
	"name": "孙昊",
	"mobile": "18612172117",
	"gender": "Male",
	"qq": "120644874",
	"parents": "妈妈",
	"parentsMobile": "13630559553",
	"province": "吉林省",
	"city": "白山市",
	"district": "浑江区",
	"school": "白山市第九中学",
	"grade": "初二",
	"gradeNote": "",
	"teachingAterial": "初二",
	"teachingAterialNote": "",
	"scores": 66,
	"fullMarks": 100,
	"comment": "刚及格",
	"tutorialFlag": false,
	"learningProcess": "无理数",
	"nextTest": "期中考试",
	"nextTestDate": "2017-10-09T16:00:00.000+0000",
	"answerInterval": "14:00-15:00",
	"ownedSalesID": 1,
	"ownedSalesName": "林依然",
	"ownedSalesUserName": "linyiran"
}
```


### 客户分配
REQ:  

POST /sales-crm/customers/distribution
```json
{
    "ownedSalesID": "2（销售id）",
    "ownedSalesName": "孙昊（销售名称）",
    "ownedSalesUserName": "sunhao（销售账户名称）",
    "customerIds": [
        "35287bb6-83c4-11e7-964c-c81f66c50f7d",
        "3cd64806-83c4-11e7-964c-c81f66c50f7d",
        "4191b4ac-83c4-11e7-964c-c81f66c50f7d",
        "49bf2265-83c4-11e7-964c-c81f66c50f7d",
		"(所有被分配客户id)"
    ]
}
```

RESP:
```json
{
    "ownedSalesID": "2（销售id）",
    "ownedSalesName": "孙昊（销售名称）",
    "ownedSalesUserName": "sunhao（销售账户名称）",
    "customerIds": [
        "35287bb6-83c4-11e7-964c-c81f66c50f7d",
        "3cd64806-83c4-11e7-964c-c81f66c50f7d",
        "4191b4ac-83c4-11e7-964c-c81f66c50f7d",
        "49bf2265-83c4-11e7-964c-c81f66c50f7d",
		"(所有被分配客户id)"
    ]
}
```

### 增加动态记录
REQ:
  
### type 1:系统日志 2:人为操作日志
POST /sales-crm/record/create 
```json
{
    "customerId": "81d32b61-83bd-11e7-964c-c81f66c50f7d",
    "comment": "续单成功",
	"type": "2"
}
```

RESP:
```json
{
    "id": "11",
    "createdDate": null,
    "createdBy": null,
    "lastModifiedDate": null,
    "lastModifiedBy": null,
    "version": 0,
    "customerId": "81d32b61-83bd-11e7-964c-c81f66c50f7d",
    "comment": "续单成功",
    "type": "2",
    "name": null,
    "userName": null
}
```


### 获取动态日志列表
REQ:
  
GET /sales-crm/record/customer-id/81d32b61-83bd-11e7-964c-c81f66c50f7d 
```json

```

RESP:
```json
[
    {
        "id": "11",
        "createdDate": "2017-08-18T09:08:50Z",
        "createdBy": "sato:user:keycloak:sales",
        "lastModifiedDate": "2017-08-18T09:08:50Z",
        "lastModifiedBy": "sato:user:keycloak:sales",
        "version": 0,
        "customerId": "81d32b61-83bd-11e7-964c-c81f66c50f7d",
        "comment": "续单成功",
        "type": "2",
        "name": null,
        "userName": "sales"
    },
    {
        "id": "7",
        "createdDate": "2017-08-18T08:58:14Z",
        "createdBy": "sato:user:keycloak:sales",
        "lastModifiedDate": "2017-08-18T08:58:14Z",
        "lastModifiedBy": "sato:user:keycloak:sales",
        "version": 0,
        "customerId": "81d32b61-83bd-11e7-964c-c81f66c50f7d",
        "comment": "准备续单",
        "type": "2",
        "name": null,
        "userName": "sales"
    },
    {
        "id": "6",
        "createdDate": "2017-08-18T08:02:49Z",
        "createdBy": "sato:user:keycloak:sales",
        "lastModifiedDate": "2017-08-18T08:02:49Z",
        "lastModifiedBy": "sato:user:keycloak:sales",
        "version": 0,
        "customerId": "81d32b61-83bd-11e7-964c-c81f66c50f7d",
        "comment": "成单",
        "type": "2",
        "name": null,
        "userName": "sales"
    },
    {
        "id": "5",
        "createdDate": "2017-08-18T08:02:42Z",
        "createdBy": "sato:user:keycloak:sales",
        "lastModifiedDate": "2017-08-18T08:02:42Z",
        "lastModifiedBy": "sato:user:keycloak:sales",
        "version": 0,
        "customerId": "81d32b61-83bd-11e7-964c-c81f66c50f7d",
        "comment": "回访完成，兴趣很大，有望促进成单",
        "type": "2",
        "name": null,
        "userName": "sales"
    },
    {
        "id": "4",
        "createdDate": "2017-08-18T08:02:15Z",
        "createdBy": "sato:user:keycloak:sales",
        "lastModifiedDate": "2017-08-18T08:02:15Z",
        "lastModifiedBy": "sato:user:keycloak:sales",
        "version": 0,
        "customerId": "81d32b61-83bd-11e7-964c-c81f66c50f7d",
        "comment": "体验课已经上完，准备和家长回访",
        "type": "2",
        "name": null,
        "userName": "sales"
    },
    {
        "id": "3",
        "createdDate": "2017-08-18T08:01:55Z",
        "createdBy": "sato:user:keycloak:sales",
        "lastModifiedDate": "2017-08-18T08:01:55Z",
        "lastModifiedBy": "sato:user:keycloak:sales",
        "version": 0,
        "customerId": "81d32b61-83bd-11e7-964c-c81f66c50f7d",
        "comment": "已联系上家长，需要上体验课看学生反馈",
        "type": "2",
        "name": null,
        "userName": "sales"
    },
    {
        "id": "2",
        "createdDate": "2017-08-18T08:01:25Z",
        "createdBy": "sato:user:keycloak:sales",
        "lastModifiedDate": "2017-08-18T08:01:25Z",
        "lastModifiedBy": "sato:user:keycloak:sales",
        "version": 0,
        "customerId": "81d32b61-83bd-11e7-964c-c81f66c50f7d",
        "comment": "学生很积极，需要和家长再联系",
        "type": "2",
        "name": null,
        "userName": "sales"
    },
    {
        "id": "1",
        "createdDate": "2017-08-18T08:00:29Z",
        "createdBy": "sato:user:keycloak:sales",
        "lastModifiedDate": "2017-08-18T08:00:29Z",
        "lastModifiedBy": "sato:user:keycloak:sales",
        "version": 0,
        "customerId": "81d32b61-83bd-11e7-964c-c81f66c50f7d",
        "comment": "创建客户",
        "type": "1",
        "name": null,
        "userName": "sales"
    }
]
```



