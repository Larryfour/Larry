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

### 获取客户管理客户列表
REQ:  

GET /sales-crm/customers?page=0&size=2&name=昊&tagIds=1&tagIds=4&tagIds=7&mobile=186&grade=初二&from=2017-03-05T16:00:00.000Z&to=2017-11-11T16:00:00.000Z&ownedSalesID=17&source=1
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
            "tagNames": "意向不确定,测试标签2",
            "source":"1",
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
            "tagNames": null,
            "source":"1",
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

### 获取我的客户客户列表
REQ:  

GET /sales-crm/customers/myself?page=0&size=2&name=昊&tagIds=1&tagIds=4&tagIds=7&mobile=186&grade=初二&from=2017-03-05T16:00:00.000Z&to=2017-11-11T16:00:00.000Z&source=1
```json
RESP:
{
    "links": [
        {
            "rel": "first",
            "href": "http://localhost:8080/sales-crm/customers/page-get/myself?page=0&size=2"
        },
        {
            "rel": "self",
            "href": "http://localhost:8080/sales-crm/customers/page-get/myself"
        },
        {
            "rel": "next",
            "href": "http://localhost:8080/sales-crm/customers/page-get/myself?page=1&size=2"
        },
        {
            "rel": "last",
            "href": "http://localhost:8080/sales-crm/customers/page-get/myself?page=1&size=2"
        }
    ],
    "content": [
        {
            "id": "49bf2265-83c4-11e7-964c-c81f66c50f7d",
            "createdDate": "2017-08-18T03:21:13Z",
            "createdBy": "sato:user:keycloak:sales",
            "lastModifiedDate": "2017-08-18T03:21:13Z",
            "lastModifiedBy": "sato:user:keycloak:sales",
            "version": 0,
            "contactName": "孙昊9",
            "contactMobile": "18612172119",
            "xuebaNo": null,
            "name": "孙昊9",
            "mobile": "18612172119",
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
            "ownedSalesID": 2,
            "ownedSalesName": "孙昊",
            "ownedSalesUserName": "sales",
            "tagNames": "意向不确定,测试标签2",
            "source":"1",
            "links": []
        },
        {
            "id": "4191b4ac-83c4-11e7-964c-c81f66c50f7d",
            "createdDate": "2017-08-18T03:20:59Z",
            "createdBy": "sato:user:keycloak:sales",
            "lastModifiedDate": "2017-08-18T03:20:59Z",
            "lastModifiedBy": "sato:user:keycloak:sales",
            "version": 0,
            "contactName": "孙昊5",
            "contactMobile": "18612172115",
            "xuebaNo": null,
            "name": "孙昊5",
            "mobile": "18612172115",
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
            "ownedSalesID": 2,
            "ownedSalesName": "孙昊",
            "ownedSalesUserName": "sales",
            "tagNames": null,
            "source":"1",
            "links": []
        }
    ],
    "page": {
        "size": 2,
        "totalElements": 4,
        "totalPages": 2,
        "number": 0
    }
}
```

### 增加客户
REQ:  

POST /sales-crm/customers
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
    "source":"1" //1.app弹出 2.app入口 3.官网 4.ec历史数据 5.后台添加
}
```

RESP:
```json
{   
    "id":"81d32b61-83bd-11e7-964c-c81f66c50f7d",
    "contactName": "孙昊",
    "contactMobile": "18612172112",
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
    "ownedSalesID":"1",
    "ownedSalesName":"销售主管名称",
    "ownedSalesUserName":"销售主管账户名称",
    "source":"1"
}
```

### 更新客户
REQ:  

PUT /sales-crm/customers/81d32b61-83bd-11e7-964c-c81f66c50f7d
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

GET /sales-crm/customers/81d32b61-83bd-11e7-964c-c81f66c50f7d
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
	"ownedSalesUserName": "linyiran",
	"source":"5"
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
POST /sales-crm/record 
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
    "id": "28",
    "createdDate": "2017-08-23T05:16:23.445Z",
    "createdBy": "sato:user:keycloak:sales",
    "lastModifiedDate": null,
    "lastModifiedBy": null,
    "version": 0,
    "customerId": "81d32b61-83bd-11e7-964c-c81f66c50f7d",
    "comment": "续单成功",
    "type": "2",
    "name": "测试",
    "userName": "sales"
}
```


### 获取动态日志列表
REQ:
  
GET /sales-crm/record/customer/81d32b61-83bd-11e7-964c-c81f66c50f7d 
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


### 获取标签管理列表
REQ:
  
GET /sales-crm/tags/management
```json

```

RESP:
```json
[
    {
        "tagGroupId": 1,
        "tagGroupName": "客户分类",
        "multiSelect": true,
        "tags": [
            {
                "id": "1",
                "createdDate": "2017-08-23T09:11:38Z",
                "createdBy": "sato:user:no-user",
                "lastModifiedDate": "2017-08-23T09:11:38Z",
                "lastModifiedBy": "sato:user:no-user",
                "version": 0,
                "name": "无法接通",
                "comment": "无法接通",
                "tagGroupId": 1,
                "flag": false
            },
            {
                "id": "2",
                "createdDate": "2017-08-23T09:11:38Z",
                "createdBy": "sato:user:no-user",
                "lastModifiedDate": "2017-08-23T09:11:38Z",
                "lastModifiedBy": "sato:user:no-user",
                "version": 0,
                "name": "错号/空号",
                "comment": "错号/空号",
                "tagGroupId": 1,
                "flag": false
            },
            {
                "id": "3",
                "createdDate": "2017-08-23T09:11:38Z",
                "createdBy": "sato:user:no-user",
                "lastModifiedDate": "2017-08-23T09:11:38Z",
                "lastModifiedBy": "sato:user:no-user",
                "version": 0,
                "name": "很有意向",
                "comment": "很有意向",
                "tagGroupId": 1,
                "flag": false
            }
        ]
    },
    {
        "tagGroupId": 2,
        "tagGroupName": "年级",
        "multiSelect": false,
        "tags": [
            {
                "id": "21",
                "createdDate": "2017-09-28T14:36:30Z",
                "createdBy": "sato:user:no-user",
                "lastModifiedDate": "2017-09-28T14:36:30Z",
                "lastModifiedBy": "sato:user:no-user",
                "version": 0,
                "name": "小学",
                "comment": "小学",
                "tagGroupId": 2,
                "flag": false
            },
            {
                "id": "22",
                "createdDate": "2017-09-28T14:36:30Z",
                "createdBy": "sato:user:no-user",
                "lastModifiedDate": "2017-09-28T14:36:30Z",
                "lastModifiedBy": "sato:user:no-user",
                "version": 0,
                "name": "初一",
                "comment": "初一",
                "tagGroupId": 2,
                "flag": false
            }
        ]
    },
    {
        "tagGroupId": 3,
        "tagGroupName": "阶段",
        "multiSelect": false,
        "tags": [
            {
                "id": "29",
                "createdDate": "2017-09-28T14:38:46Z",
                "createdBy": "sato:user:no-user",
                "lastModifiedDate": "2017-09-28T14:38:46Z",
                "lastModifiedBy": "sato:user:no-user",
                "version": 0,
                "name": "课前联系学生",
                "comment": "课前联系学生",
                "tagGroupId": 3,
                "flag": false
            },
            {
                "id": "30",
                "createdDate": "2017-09-28T14:38:46Z",
                "createdBy": "sato:user:no-user",
                "lastModifiedDate": "2017-09-28T14:38:46Z",
                "lastModifiedBy": "sato:user:no-user",
                "version": 0,
                "name": "课前家访",
                "comment": "课前家访",
                "tagGroupId": 3,
                "flag": false
            }
        ]
    },
    {
        "tagGroupId": 4,
        "tagGroupName": "性质",
        "multiSelect": false,
        "tags": [
            {
                "id": "37",
                "createdDate": "2017-09-28T14:38:46Z",
                "createdBy": "sato:user:no-user",
                "lastModifiedDate": "2017-09-28T14:38:46Z",
                "lastModifiedBy": "sato:user:no-user",
                "version": 0,
                "name": "走读",
                "comment": "走读",
                "tagGroupId": 4,
                "flag": false
            },
            {
                "id": "38",
                "createdDate": "2017-09-28T14:38:46Z",
                "createdBy": "sato:user:no-user",
                "lastModifiedDate": "2017-09-28T14:38:46Z",
                "lastModifiedBy": "sato:user:no-user",
                "version": 0,
                "name": "住宿",
                "comment": "住宿",
                "tagGroupId": 4,
                "flag": false
            }
        ]
    }
]
```

### 客户标签设置
REQ:
  
POST /sales-crm/tags/customer/81d32b61-83bd-11e7-964c-c81f66c50f7d/setting
```json
{
    "setTags": [
        {
            "id": "4",
            "name": "意向不确定"
        },
        {
            "id": "7",
            "name": "测试标签2"
        }
    ],
    "cancelTags": [
        {
            "id": "3",
            "name": "很有意向"
        }
    ]
}
```

RESP:
```json
{
    "customerId": "81d32b61-83bd-11e7-964c-c81f66c50f7d",
    "setTags": [
        {
            "id": "4",
            "name": "意向不确定"
        },
        {
            "id": "7",
            "name": "测试标签2"
        }
    ],
    "cancelTags": [
        {
            "id": "3",
            "name": "很有意向"
        }
    ]
}
```


### 获取客户标签列表
REQ:
  
GET /sales-crm/tags/customer/81d32b61-83bd-11e7-964c-c81f66c50f7d/myself
```json

```

RESP:
```json
[
    {
        "customerId": "81d32b61-83bd-11e7-964c-c81f66c50f7d",
        "tagId": "1",
        "tagName": "无法接通"
    },
    {
        "customerId": "81d32b61-83bd-11e7-964c-c81f66c50f7d",
        "tagId": "2",
        "tagName": "错号/空号"
    },
    {
        "customerId": "81d32b61-83bd-11e7-964c-c81f66c50f7d",
        "tagId": "4",
        "tagName": "意向不确定"
    },
    {
        "customerId": "81d32b61-83bd-11e7-964c-c81f66c50f7d",
        "tagId": "5",
        "tagName": "明确拒绝"
    }
]
```

### 启动约课
REQ:
  
POST /sales-crm/bpmn/experience/book-course/21d6ef54-9234-11e7-a2ef-c81f66c50f7d
```json
{
}
```

RESP:
```json
{
    "id": "21d6ef54-9234-11e7-a2ef-c81f66c50f7d",
    "createdDate": "2017-09-05T12:17:06Z",
    "createdBy": "sato:user:keycloak:service-account-sales-leads-http",
    "lastModifiedDate": "2017-09-05T12:17:06Z",
    "lastModifiedBy": "sato:user:keycloak:service-account-sales-leads-http",
    "version": 0,
    "contactName": "党梦鸽",
    "contactMobile": "18632146587",
    "xuebaNo": 14,
    "name": "党梦鸽",
    "mobile": "18632146587",
    "gender": "Female",
    "qq": "2524912818",
    "parents": "父亲",
    "parentsMobile": "18536549853",
    "province": "黑龙江",
    "city": "双鸭山",
    "district": null,
    "school": "双鸭山市第十八中学",
    "grade": "初二",
    "gradeNote": null,
    "teachingAterial": "初二",
    "teachingAterialNote": "八年级第十二章 全等三角形",
    "scores": 66,
    "fullMarks": 100,
    "comment": "刚及格",
    "tutorialFlag": true,
    "learningProcess": "无理数",
    "nextTest": "期中考试",
    "nextTestDate": "2017-10-09T16:00:00.000+0000",
    "answerInterval": "2017-9-6 15:00-16:00",
    "ownedSalesID": null,
    "ownedSalesName": null,
    "ownedSalesUserName": null,
    "source": "3"
}
```

### 获取个人销售日报
REQ:
  
GET /sales-crm/report/sales/daily/myself?from=2017-10-15T16:00:00.000Z&to=2017-10-17T16:00:00.000Z
```json

```

RESP:
```json
{
    "data": [
        {
            "date": "2017-10-16",
            "distributedNumber": 0,
            "bridgeDuration": 95,
            "offset": 0,
            "rewards": 0,
            "offsetAfter": 0,
            "reservedCount": 1,
            "completedCount": 1,
            "firstOrderCount": 0,
            "firstOrderAmount": 0,
            "repeatOrderCount": 0,
            "repeatOrderAmount": 0,
            "comment": null
        },
        {
            "date": "2017-10-17",
            "distributedNumber": 1,
            "bridgeDuration": 133,
            "offset": 17,
            "rewards": 0,
            "offsetAfter": -17,
            "reservedCount": 1,
            "completedCount": 1,
            "firstOrderCount": 1,
            "firstOrderAmount": 312,
            "repeatOrderCount": 1,
            "repeatOrderAmount": 312,
            "comment": null
        }
    ]
}
```


### 七天新生呼出率
REQ:
  
GET /sales-crm/report/sales/daily/seven-day/call-rate?dailyDate=2017-12-13
```json

```

RESP:
```json
{
    "records": [
        {
            "name": "刘香媛",
            "callOutNumber": 99,
            "connectedNumber": 44,
            "moreFiveMinutesNumber": 30,
            "moreThreeMinutesNumber": 34,
            "moreOneMinutesNumber": 37
        },
        {
            "name": "许世伟",
            "callOutNumber": 119,
            "connectedNumber": 69,
            "moreFiveMinutesNumber": 40,
            "moreThreeMinutesNumber": 46,
            "moreOneMinutesNumber": 53
        },
        {
            "name": "康学辉",
            "callOutNumber": 148,
            "connectedNumber": 56,
            "moreFiveMinutesNumber": 26,
            "moreThreeMinutesNumber": 34,
            "moreOneMinutesNumber": 41
        },
        {
            "name": "陈富",
            "callOutNumber": 223,
            "connectedNumber": 87,
            "moreFiveMinutesNumber": 30,
            "moreThreeMinutesNumber": 38,
            "moreOneMinutesNumber": 60
        }
    ]
}
```



### 获取销售管理报表
REQ:
  
GET /sales-crm/report/sales/daily?dailyDate=2017-10-17
```json

```

RESP:
```json
{
    "daily": {
        "dailyNew": {
            "callerCount": 2,
            "distributionCount": 1,
            "completedCount": 1,
            "orderCount": 2,
            "orderAmount": 624
        },
        "dailyReNew": {
            "callerCount": 1,
            "orderCount": 0,
            "orderAmount": 0
        }
    },
    "personalDaily": [
        {
            "distributedNumber": 0,
            "bridgeDuration": 0,
            "offset": 0,
            "rewards": 0,
            "offsetAfter": 0,
            "reservedCount": 0,
            "completedCount": 0,
            "firstOrderCount": 0,
            "firstOrderAmount": 0,
            "repeatOrderCount": 0,
            "repeatOrderAmount": 0,
            "salesId": "6",
            "salesName": "巩学枝",
            "groupId": 2,
            "comment": null
        },
        {
            "distributedNumber": 0,
            "bridgeDuration": 0,
            "offset": 0,
            "rewards": 0,
            "offsetAfter": 0,
            "reservedCount": 0,
            "completedCount": 0,
            "firstOrderCount": 0,
            "firstOrderAmount": 0,
            "repeatOrderCount": 0,
            "repeatOrderAmount": 0,
            "salesId": "14",
            "salesName": "许世伟",
            "groupId": 1,
            "comment": null
        },
        {
            "distributedNumber": 1,
            "bridgeDuration": 16,
            "offset": 17,
            "rewards": 0,
            "offsetAfter": -17,
            "reservedCount": 1,
            "completedCount": 1,
            "firstOrderCount": 1,
            "firstOrderAmount": 312,
            "repeatOrderCount": 1,
            "repeatOrderAmount": 312,
            "salesId": "17",
            "salesName": "测试",
            "groupId": 1,
            "comment": null
        }
    ],
    "month": {
        "monthNews": {
            "targetOrderCount": 15,
            "completedOrderCount": 2,
            "orderCompletedPercent": "13.33",
            "targetAmount": 1500,
            "completedAmount": 624,
            "amountCompletedPercent": "41.60"
        },
        "monthReNews": {
            "targetOrderCount": 6,
            "completedOrderCount": 0,
            "orderCompletedPercent": "0.00",
            "targetAmount": 600,
            "completedAmount": 0,
            "amountCompletedPercent": "0.00"
        },
        "teacherRepeatOrder":{
            "orderCount": 1,
            "orderAmount": 312,
            "totalAmount": 312
        }
    },
    "personalMonth": [
        {
            "salesId": "6",
            "groupId": 2,
            "name": "巩学枝",
            "targetAmount": 600,
            "completedAmount": 0,
            "amountCompletedPercent": "0.00",
            "targetOrderCount": 6,
            "completedOrderCount": 0,
            "orderCompletedPercent": "0.00"
        },
        {
            "salesId": "14",
            "groupId": 1,
            "name": "许世伟",
            "targetAmount": 500,
            "completedAmount": 0,
            "amountCompletedPercent": "0.00",
            "targetOrderCount": 5,
            "completedOrderCount": 0,
            "orderCompletedPercent": "0.00"
        },
        {
            "salesId": "17",
            "groupId": 1,
            "name": "测试",
            "targetAmount": 1000,
            "completedAmount": 624,
            "amountCompletedPercent": "62.40",
            "targetOrderCount": 10,
            "completedOrderCount": 2,
            "orderCompletedPercent": "20.00"
        }
    ]
}
```

### 增加抵消时长
REQ:
  
Post /sales-crm/offset
```json
{
    "offset": 10,
    "offsetDate":"2017-12-02",
    "comment":"测试Comment"
}
```

RESP:
```json
{
    "id": null,
    "salesId": 99,
    "offsetBefore": 0,
    "offsetAfter": 0,
    "rewards": 0,
    "offset": 10,
    "offsetDate": "2017-12-02T00:00:00.000+0000",
    "status": false,
    "comment":"测试Comment"
}
```


### 获取指定销售指定日子的抵消明细
REQ:
  
GET /sales-crm/offset/date/2017-12-01
```json

```

RESP:
```json
{
    "id": 1,
    "salesId": 99,
    "offsetBefore": 0,
    "offsetAfter": 20,
    "rewards": 0,
    "offset": 30,
    "offsetDate": "2017-11-30T16:00:00.000+0000",
    "status": false,
    "comment": null
}
```

### 获取付费客户信息
REQ:
  
GET /sales-crm/customers/paying?xuebaNo=22834921&mobile=18612172117&grade=初二&name=张三&parentMobile=18611112222&resetHours=3&tagIds=1&tagIds=2
```json

```

RESP:
```json
{
    "links": [
        {
            "rel": "first",
            "href": "http://localhost:8080/sales-crm/customers/paying?page=0&size=10"
        },
        {
            "rel": "self",
            "href": "http://localhost:8080/sales-crm/customers/paying"
        },
        {
            "rel": "next",
            "href": "http://localhost:8080/sales-crm/customers/paying?page=1&size=10"
        },
        {
            "rel": "last",
            "href": "http://localhost:8080/sales-crm/customers/paying?page=43&size=10"
        }
    ],
    "content": [
        {
            "id": "2e58670f-c2c9-11e7-bc4a-d8cb8afa8d9a",
            "createdDate": "2017-09-16T11:58:04Z",
            "createdBy": "sato:user:keycloak:gongxuezhi",
            "lastModifiedDate": "2017-09-16T11:59:21Z",
            "lastModifiedBy": "sato:user:keycloak:xushiwei",
            "version": 0,
            "contactName": "2338267",
            "contactMobile": "2338267",
            "xuebaNo": 23382674,
            "name": "陈萌",
            "mobile": "18747634966",
            "gender": "Female",
            "qq": "3405462651",
            "parents": null,
            "parentsMobile": "18747634966",
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
            "ownedSalesID": null,
            "ownedSalesName": null,
            "ownedSalesUserName": null,
            "distributedDate": null,
            "distributedBy": null,
            "source": "4",
            "totalHours": 10,
            "resetHours": 6,
            "nceetime": null,
            "tagNames": null,
            "links": []
        },
        {
            "id": "22c4fe40-c2c9-11e7-ad0c-d8cb8afa8d9a",
            "createdDate": "2017-09-13T13:39:56Z",
            "createdBy": "sato:user:keycloak:gongxuezhi",
            "lastModifiedDate": "2017-09-29T05:34:19Z",
            "lastModifiedBy": "sato:user:keycloak:xushiwei",
            "version": 0,
            "contactName": "2331303",
            "contactMobile": "2331303",
            "xuebaNo": 23313036,
            "name": "张业健",
            "mobile": "13030333330",
            "gender": "Male",
            "qq": "2518523719",
            "parents": null,
            "parentsMobile": "13080118869",
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
            "ownedSalesID": null,
            "ownedSalesName": null,
            "ownedSalesUserName": null,
            "distributedDate": null,
            "distributedBy": null,
            "source": "4",
            "totalHours": 46,
            "resetHours": 35,
            "nceetime": null,
            "tagNames": null,
            "links": []
        },
        {
            "id": "a9db4ade-98f5-11e7-b270-005056c00008",
            "createdDate": "2017-09-11T14:01:13Z",
            "createdBy": "sato:user:no-user",
            "lastModifiedDate": "2017-09-19T03:40:42Z",
            "lastModifiedBy": "sato:user:keycloak:sales01",
            "version": 0,
            "contactName": "常雅静",
            "contactMobile": "13546454220",
            "xuebaNo": 23271482,
            "name": "常雅静",
            "mobile": "13546454220",
            "gender": null,
            "qq": "3416651715",
            "parents": "母亲",
            "parentsMobile": "\t15234255565",
            "province": null,
            "city": null,
            "district": null,
            "school": null,
            "grade": null,
            "gradeNote": null,
            "teachingAterial": null,
            "teachingAterialNote": "七年级第一章 有理数",
            "scores": null,
            "fullMarks": null,
            "comment": "",
            "tutorialFlag": false,
            "learningProcess": null,
            "nextTest": null,
            "nextTestDate": null,
            "answerInterval": "2017-9-12 12:00-13:00",
            "ownedSalesID": 16,
            "ownedSalesName": "测试主管",
            "ownedSalesUserName": "sales01",
            "distributedDate": null,
            "distributedBy": null,
            "source": "4",
            "totalHours": 10,
            "resetHours": 4,
            "nceetime": null,
            "tagNames": null,
            "links": []
        },
        {
            "id": "ac262540-98f5-11e7-b9f8-005056c00008",
            "createdDate": "2017-09-10T15:02:07Z",
            "createdBy": "sato:user:no-user",
            "lastModifiedDate": "2017-09-10T15:02:07Z",
            "lastModifiedBy": "sato:user:no-user",
            "version": 0,
            "contactName": "肖雨洁",
            "contactMobile": "18582267190",
            "xuebaNo": 23251383,
            "name": "肖雨洁",
            "mobile": "18582267190",
            "gender": null,
            "qq": "3167868195",
            "parents": "父亲",
            "parentsMobile": "\t18381720908",
            "province": null,
            "city": null,
            "district": null,
            "school": null,
            "grade": null,
            "gradeNote": null,
            "teachingAterial": null,
            "teachingAterialNote": "八年级第十四章 整式的乘法与因式分解",
            "scores": null,
            "fullMarks": null,
            "comment": "",
            "tutorialFlag": false,
            "learningProcess": null,
            "nextTest": null,
            "nextTestDate": null,
            "answerInterval": "2017-9-11 20:00-21:00",
            "ownedSalesID": 11,
            "ownedSalesName": "魏萌萌",
            "ownedSalesUserName": "weimengmeng",
            "distributedDate": null,
            "distributedBy": null,
            "source": "4",
            "totalHours": 10,
            "resetHours": 6,
            "nceetime": null,
            "tagNames": null,
            "links": []
        },
        {
            "id": "ac576e70-98f5-11e7-9d91-005056c00008",
            "createdDate": "2017-09-10T13:44:47Z",
            "createdBy": "sato:user:no-user",
            "lastModifiedDate": "2017-09-10T13:44:47Z",
            "lastModifiedBy": "sato:user:no-user",
            "version": 0,
            "contactName": "梅志豪",
            "contactMobile": "18506148723",
            "xuebaNo": 23260068,
            "name": "梅志豪",
            "mobile": "18506148723",
            "gender": null,
            "qq": "1534842973",
            "parents": "父亲",
            "parentsMobile": "\t13862140942",
            "province": null,
            "city": null,
            "district": null,
            "school": null,
            "grade": null,
            "gradeNote": null,
            "teachingAterial": null,
            "teachingAterialNote": "九年级第二十二章 二次函数",
            "scores": null,
            "fullMarks": null,
            "comment": "",
            "tutorialFlag": false,
            "learningProcess": null,
            "nextTest": null,
            "nextTestDate": null,
            "answerInterval": "2017-9-11 19:00-20:00",
            "ownedSalesID": 11,
            "ownedSalesName": "魏萌萌",
            "ownedSalesUserName": "weimengmeng",
            "distributedDate": null,
            "distributedBy": null,
            "source": "4",
            "totalHours": 10,
            "resetHours": 4,
            "nceetime": null,
            "tagNames": null,
            "links": []
        },
        {
            "id": "ace75261-98f5-11e7-8d57-005056c00008",
            "createdDate": "2017-09-10T11:43:17Z",
            "createdBy": "sato:user:no-user",
            "lastModifiedDate": "2017-09-10T11:43:17Z",
            "lastModifiedBy": "sato:user:no-user",
            "version": 0,
            "contactName": "酸果子",
            "contactMobile": "13846723165",
            "xuebaNo": 22824539,
            "name": "酸果子",
            "mobile": "13846723165",
            "gender": null,
            "qq": "3345190768",
            "parents": "母亲",
            "parentsMobile": "\t18346456042",
            "province": null,
            "city": null,
            "district": null,
            "school": null,
            "grade": null,
            "gradeNote": null,
            "teachingAterial": null,
            "teachingAterialNote": "八年级第十一章 三角形",
            "scores": null,
            "fullMarks": null,
            "comment": "初二,约课成功",
            "tutorialFlag": false,
            "learningProcess": null,
            "nextTest": null,
            "nextTestDate": null,
            "answerInterval": "2017-9-11 19:00-20:00",
            "ownedSalesID": 7,
            "ownedSalesName": "刘香媛",
            "ownedSalesUserName": "liuxiangyuan",
            "distributedDate": null,
            "distributedBy": null,
            "source": "4",
            "totalHours": 10,
            "resetHours": 8,
            "nceetime": null,
            "tagNames": null,
            "links": []
        },
        {
            "id": "b3b9d7c0-98f5-11e7-abab-005056c00008",
            "createdDate": "2017-09-09T03:52:17Z",
            "createdBy": "sato:user:no-user",
            "lastModifiedDate": "2017-09-09T03:52:17Z",
            "lastModifiedBy": "sato:user:no-user",
            "version": 0,
            "contactName": "陈艳艳",
            "contactMobile": "15207890433",
            "xuebaNo": 23130101,
            "name": "陈艳艳",
            "mobile": "15207890433",
            "gender": null,
            "qq": "1641943560",
            "parents": "母亲",
            "parentsMobile": "\t13978925609",
            "province": null,
            "city": null,
            "district": null,
            "school": null,
            "grade": null,
            "gradeNote": null,
            "teachingAterial": null,
            "teachingAterialNote": "九年级第二十一章 一元二次方程",
            "scores": null,
            "fullMarks": null,
            "comment": "意向弱\n九年级第二十一章 一元二次方程 20 120 初一 都可以 十点到十二点都是以 人教版 一个星期",
            "tutorialFlag": false,
            "learningProcess": null,
            "nextTest": null,
            "nextTestDate": null,
            "answerInterval": "2017-9-9 13:00-14:00",
            "ownedSalesID": 12,
            "ownedSalesName": "许世伟",
            "ownedSalesUserName": "xushiwei",
            "distributedDate": null,
            "distributedBy": null,
            "source": "4",
            "totalHours": 10,
            "resetHours": 6,
            "nceetime": null,
            "tagNames": null,
            "links": []
        },
        {
            "id": "b634c2cf-98f5-11e7-b205-005056c00008",
            "createdDate": "2017-09-08T12:26:58Z",
            "createdBy": "sato:user:no-user",
            "lastModifiedDate": "2017-09-08T12:26:58Z",
            "lastModifiedBy": "sato:user:no-user",
            "version": 0,
            "contactName": "王博钦",
            "contactMobile": "18956946078",
            "xuebaNo": 17769773,
            "name": "王博钦",
            "mobile": "18956946078",
            "gender": null,
            "qq": "3025142877",
            "parents": "母亲",
            "parentsMobile": "\t15135596796",
            "province": null,
            "city": null,
            "district": null,
            "school": null,
            "grade": null,
            "gradeNote": null,
            "teachingAterial": null,
            "teachingAterialNote": "九年级第二十一章 一元二次方程",
            "scores": null,
            "fullMarks": null,
            "comment": "初三,约课成功",
            "tutorialFlag": false,
            "learningProcess": null,
            "nextTest": null,
            "nextTestDate": null,
            "answerInterval": "2017-9-9 12:00-13:00",
            "ownedSalesID": 3,
            "ownedSalesName": "冯海利",
            "ownedSalesUserName": "fenghaili",
            "distributedDate": null,
            "distributedBy": null,
            "source": "4",
            "totalHours": 10,
            "resetHours": 6,
            "nceetime": null,
            "tagNames": null,
            "links": []
        },
        {
            "id": "2aae23c0-c2c9-11e7-985a-d8cb8afa8d9a",
            "createdDate": "2017-09-08T10:55:10Z",
            "createdBy": "sato:user:keycloak:gongxuezhi",
            "lastModifiedDate": "2017-09-18T07:00:25Z",
            "lastModifiedBy": "sato:user:keycloak:gongxuezhi",
            "version": 0,
            "contactName": "2322850",
            "contactMobile": "2322850",
            "xuebaNo": 23228507,
            "name": "夏豪昊",
            "mobile": "15027338902",
            "gender": "Male",
            "qq": "2295840711",
            "parents": null,
            "parentsMobile": "15027253188",
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
            "ownedSalesID": null,
            "ownedSalesName": null,
            "ownedSalesUserName": null,
            "distributedDate": null,
            "distributedBy": null,
            "source": "4",
            "totalHours": 10,
            "resetHours": 8,
            "nceetime": null,
            "tagNames": null,
            "links": []
        },
        {
            "id": "b76af8de-98f5-11e7-bc39-005056c00008",
            "createdDate": "2017-09-08T09:39:09Z",
            "createdBy": "sato:user:no-user",
            "lastModifiedDate": "2017-09-08T09:39:09Z",
            "lastModifiedBy": "sato:user:no-user",
            "version": 0,
            "contactName": "许可",
            "contactMobile": "17375147306",
            "xuebaNo": 23039877,
            "name": "许可",
            "mobile": "17375147306",
            "gender": null,
            "qq": "3341535158",
            "parents": "母亲",
            "parentsMobile": "\t13549557537",
            "province": null,
            "city": null,
            "district": null,
            "school": null,
            "grade": null,
            "gradeNote": null,
            "teachingAterial": null,
            "teachingAterialNote": "八年级第十五章 分式",
            "scores": null,
            "fullMarks": null,
            "comment": "初二,约课成功,住宿",
            "tutorialFlag": false,
            "learningProcess": null,
            "nextTest": null,
            "nextTestDate": null,
            "answerInterval": "2017-9-9 14:00-15:00",
            "ownedSalesID": 7,
            "ownedSalesName": "刘香媛",
            "ownedSalesUserName": "liuxiangyuan",
            "distributedDate": null,
            "distributedBy": null,
            "source": "4",
            "totalHours": 10,
            "resetHours": 6,
            "nceetime": null,
            "tagNames": null,
            "links": []
        }
    ],
    "page": {
        "size": 10,
        "totalElements": 437,
        "totalPages": 44,
        "number": 0
    }
}
```

### 获取个人付费客户信息
REQ:
  
GET /sales-crm/customers/paying/myself?xuebaNo=22834921&mobile=18612172117&grade=初二&name=张三&parentMobile=18611112222&resetHours=3&tagIds=1&tagIds=2
```json

```

RESP:
```json
{
    "links": [
        {
            "rel": "self",
            "href": "http://localhost:8080/sales-crm/customers/paying/myself"
        }
    ],
    "content": [
        {
            "id": "a9db4ade-98f5-11e7-b270-005056c00008",
            "createdDate": "2017-09-11T14:01:13Z",
            "createdBy": "sato:user:no-user",
            "lastModifiedDate": "2017-09-19T03:40:42Z",
            "lastModifiedBy": "sato:user:keycloak:sales01",
            "version": 0,
            "contactName": "常雅静",
            "contactMobile": "13546454220",
            "xuebaNo": 23271482,
            "name": "常雅静",
            "mobile": "13546454220",
            "gender": null,
            "qq": "3416651715",
            "parents": "母亲",
            "parentsMobile": "\t15234255565",
            "province": null,
            "city": null,
            "district": null,
            "school": null,
            "grade": null,
            "gradeNote": null,
            "teachingAterial": null,
            "teachingAterialNote": "七年级第一章 有理数",
            "scores": null,
            "fullMarks": null,
            "comment": "",
            "tutorialFlag": false,
            "learningProcess": null,
            "nextTest": null,
            "nextTestDate": null,
            "answerInterval": "2017-9-12 12:00-13:00",
            "ownedSalesID": 16,
            "ownedSalesName": "测试主管",
            "ownedSalesUserName": "sales01",
            "distributedDate": null,
            "distributedBy": null,
            "source": "4",
            "totalHours": 10,
            "resetHours": 4,
            "nceetime": null,
            "tagNames": null,
            "links": []
        }
    ],
    "page": {
        "size": 10,
        "totalElements": 1,
        "totalPages": 1,
        "number": 0
    }
}
```

### 获取月销售目标
REQ:
  
GET /sales-crm/sales/2018-01/target
```json

```

RESP:
```json
[
    {
        "id": 17,
        "salesId": "4",
        "salesName": "张三",
        "targetAmount": 10040,
        "targetOrders": 20,
        "targetMonth": "2018-01"
    },
    {
        "id": 18,
        "salesId": "7",
        "salesName": "李四",
        "targetAmount": 5000,
        "targetOrders": 8,
        "targetMonth": "2018-01"
    }
]
```


### 设置销售目标
REQ:
  
POST /sales-crm/sales/target
```json
{
    "targetMonth": "2018-03",
    "createTargets": [
        {
            "salesId": 12,
            "targetAmount": 9000,
            "targetOrders": 5,
            "salesName": "张三"
        }
    ],
    "deleteTargetIds": [
        23
    ],
    "updateTargets": [
        {
            "targetAmount": 900000,
            "targetOrders": 1000,
            "id": 24
        }
    ]
}
```

RESP:
```json
[
    {
        "id": 24,
        "salesId": "7",
        "groupId": null,
        "targetAmount": 900000,
        "targetOrders": 1000,
        "targetMonth": "2018-03"
    },
    {
        "id": 27,
        "salesId": "12",
        "groupId": null,
        "targetAmount": 9000,
        "targetOrders": 5,
        "targetMonth": "2018-03"
    }
]
```

### 获取电话销售销售列表
REQ:
  
GET /sales-crm/sales/telephone
```json

```

RESP:
```json
[
    {
        "id": "4",
        "createdDate": "2017-08-22T04:06:32Z",
        "createdBy": "sato:user:no-user",
        "lastModifiedDate": "2017-08-22T04:06:32Z",
        "lastModifiedBy": "sato:user:no-user",
        "version": 0,
        "name": "巩学枝",
        "userName": "gongxuezhi",
        "mobile": "18518486086",
        "groupId": 1
    },
    {
        "id": "7",
        "createdDate": "2017-08-22T04:06:32Z",
        "createdBy": "sato:user:no-user",
        "lastModifiedDate": "2017-08-22T04:06:32Z",
        "lastModifiedBy": "sato:user:no-user",
        "version": 0,
        "name": "刘香媛",
        "userName": "liuxiangyuan",
        "mobile": "13810336820",
        "groupId": 1
    }
]
```



