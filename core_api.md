# 호패 코어 API 문서

## [Swagger](https://app.swaggerhub.com/apis-docs/BU-Link-3/Hopae.API/1.0.0)

## Index
- [호패 코어 API 문서](#호패-코어-api-문서)
- [Issuer](#issuer)
  - [세션 생성 요청 ✅](#세션-생성-요청-)
    - [GET /api/did/issuer/create-session](#get-apididissuercreate-session)
    - [요청](#요청)
    - [응답 (OK 200)](#응답-ok-200)
    - [응답 (Internal Server Error 500)](#응답-internal-server-error-500)
  - [Invitation 생성 요청 ✅](#invitation-생성-요청-)
    - [GET /api/did/issuer/create-invitation](#get-apididissuercreate-invitation)
    - [요청](#요청-1)
    - [응답 (OK 200)](#응답-ok-200-1)
    - [응답 (Internal Server Error 500)](#응답-internal-server-error-500-1)
  - [Invitation 수락 요청 ✅](#invitation-수락-요청-)
    - [POST /api/did/issuer/receive-invitation](#post-apididissuerreceive-invitation)
    - [요청](#요청-2)
    - [응답 (OK 200)](#응답-ok-200-2)
    - [응답 (Internal Server Error 500)](#응답-internal-server-error-500-2)
  - [Schema 생성 요청 ✅](#schema-생성-요청-)
    - [GET /api/did/issuer/create-schema](#get-apididissuercreate-schema)
    - [요청](#요청-3)
    - [응답 (OK 200)](#응답-ok-200-3)
    - [응답 (Internal Server Error 500)](#응답-internal-server-error-500-3)
  - [Credential Definition 생성 요청 ✅](#credential-definition-생성-요청-)
    - [GET /api/did/issuer/create-credential-def](#get-apididissuercreate-credential-def)
    - [요청](#요청-4)
    - [응답 (OK 200)](#응답-ok-200-4)
    - [응답 (Internal Server Error 500)](#응답-internal-server-error-500-4)
  - [Credential 발급 요청 ✅](#credential-발급-요청-)
    - [GET /api/did/issuer/issue-credential](#get-apididissuerissue-credential)
    - [요청](#요청-5)
    - [응답 (OK 200)](#응답-ok-200-5)
    - [응답 (Internal Server Error 500)](#응답-internal-server-error-500-5)
  - [Credential Revoke 요청 ✅](#credential-revoke-요청-)
    - [GET /api/did/issuer/revoke-credential](#get-apididissuerrevoke-credential)
    - [요청](#요청-6)
    - [응답 (OK 200)](#응답-ok-200-6)
- [Verifier](#verifier)
  - [Proof 요청 ✅](#proof-요청-)
    - [GET /api/did/verifier/prove](#get-apididverifierprove)
    - [요청](#요청-7)
    - [응답 (OK 200)](#응답-ok-200-7)
    - [응답 (Internal Server Error 500)](#응답-internal-server-error-500-6)
  - [Proof Verified 요청 ✅](#proof-verified-요청-)
    - [GET /api/did/verifier/verified](#get-apididverifierverified)
    - [요청](#요청-8)
    - [응답 (OK 200)](#응답-ok-200-8)

<br/>

# Issuer

## 세션 생성 요청 ✅
### GET /api/did/issuer/create-session
`(예시) /api/did/issuer/create-session?univerGu=1&userId=201621234&userPw=qwerty123456`

<br/>

### 요청

|  Query   |  Type  |   Description    | Required |
| :------: | :----: | :--------------: | :------: |
| univerGu |  int   |   Type of user   |    O     |
|  userId  | String | User(Student) Id |    O     |
|  userPw  | String |  User Password   |    O     |

### 응답 (OK 200)
```
{
    "port": 1000,
    "alias": "hopae-20161234-8156d087-0000-0000-0000-37090031466c"
}
```

|  Key  |  Type  |    Description     | Nullable |
| :---: | :----: | :----------------: | :------: |
| port  |  int   |     Agent Port     |    -     |
| alias | String | Unique ID of Agent |    -     |

### 응답 (Internal Server Error 500)

|          Case           |       Description        |
| :---------------------: | :----------------------: |
|      Login Failure      | Failed to request login  |
| Login Info is not valid | Failed to get login info |

<br/>

## Invitation 생성 요청 ✅
### GET /api/did/issuer/create-invitation
`(예시) /api/did/issuer/create-invitation`

<br/>

### 요청

| Header |    Key     | Description | Required |
| :----: | :--------: | :---------: | :------: |
| Cookie | JSESSIONID | Session ID  |    O     |

### 응답 (OK 200)
```
{
    "connection_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "invitation": {
        "@id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "@type": "https://didcomm.org/my-family/1.0/my-message-type",
        "did": "WgWxqztrNooG92RXvxSTWv",
        "imageUrl": "http://192.168.56.101/img/logo.jpg",
        "label": "Bob",
        "recipientKeys": [
            "H3C2AVvLMv6gmMNam3uVAjZpfkcJCwDwnZn6z3wXmqPV"
        ],
        "routingKeys": [
            "H3C2AVvLMv6gmMNam3uVAjZpfkcJCwDwnZn6z3wXmqPV"
        ],
        "serviceEndpoint": "http://192.168.56.101:8020"
    },
    "invitation_url": "http://192.168.56.101:8020/invite?c_i=eyJAdHlwZSI6Li4ufQ=="
}
```

|    Body    | Type  | Description | Nullable |
| :--------: | :---: | :---------: | :------: |
| Invitation | JSON  | Invitation  |    -     |

### 응답 (Internal Server Error 500)

|          Case           |       Description        |
| :---------------------: | :----------------------: |
| Login Info is not valid | Failed to get login info |

<br/>

## Invitation 수락 요청 ✅
### POST /api/did/issuer/receive-invitation
`(예시) /api/did/issuer/receive-invitation`

<br/>

### 요청
```
{
    "@id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "@type": "https://didcomm.org/my-family/1.0/my-message-type",
    "did": "WgWxqztrNooG92RXvxSTWv",
    "imageUrl": "http://192.168.56.101/img/logo.jpg",
    "label": "Bob",
    "recipientKeys": [
        "H3C2AVvLMv6gmMNam3uVAjZpfkcJCwDwnZn6z3wXmqPV"
    ],
    "routingKeys": [
        "H3C2AVvLMv6gmMNam3uVAjZpfkcJCwDwnZn6z3wXmqPV"
    ],
    "serviceEndpoint": "http://192.168.56.101:8020"
}
```

| Header |    Key     | Description | Required |
| :----: | :--------: | :---------: | :------: |
| Cookie | JSESSIONID | Session ID  |    O     |

|    Body    | Type  | Description | Required |
| :--------: | :---: | :---------: | :------: |
| Invitation | JSON  | Invitation  |    O     |

### 응답 (OK 200)
```
{
    "accept": "auto",
    "alias": "Bob, providing quotes",
    "connection_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "created_at": "2021-06-06 11:05:03Z",
    "error_msg": "No DIDDoc provided; cannot connect to public DID",
    "inbound_connection_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "invitation_key": "H3C2AVvLMv6gmMNam3uVAjZpfkcJCwDwnZn6z3wXmqPV",
    "invitation_mode": "once",
    "invitation_msg_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "my_did": "WgWxqztrNooG92RXvxSTWv",
    "request_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "rfc23_state": "invitation-sent",
    "routing_state": "active",
    "state": "active",
    "their_did": "WgWxqztrNooG92RXvxSTWv",
    "their_label": "Bob",
    "their_public_did": "2cpBmR3FqGKWi5EyUbpRY8",
    "their_role": "requester",
    "updated_at": "2021-06-06 11:05:03Z"
}
```

|    Body    | Type  |    Description    | Nullable |
| :--------: | :---: | :---------------: | :------: |
| ConnRecord | JSON  | Connection Record |    -     |

### 응답 (Internal Server Error 500)

|          Case           |       Description        |
| :---------------------: | :----------------------: |
| Login Info is not valid | Failed to get login info |

<br/>

## Schema 생성 요청 ✅
### GET /api/did/issuer/create-schema
`(예시) /api/did/issuer/create-schema`

<br/>

### 요청

| Header |    Key     | Description | Required |
| :----: | :--------: | :---------: | :------: |
| Cookie | JSESSIONID | Session ID  |    O     |

### 응답 (OK 200)
```
{
    "schema_id": "WgWxqztrNooG92RXvxSTWv:2:hopae_pass:1.0"
}
```

|    Key    |  Type  | Description | Nullable |
| :-------: | :----: | :---------: | :------: |
| schema_id | String |  Schema ID  |    -     |

### 응답 (Internal Server Error 500)

|          Case           |       Description        |
| :---------------------: | :----------------------: |
| Login Info is not valid | Failed to get login info |

<br/>

## Credential Definition 생성 요청 ✅
### GET /api/did/issuer/create-credential-def
`(예시) /api/did/issuer/create-credential-def`

<br/>

### 요청

| Header |    Key     | Description | Required |
| :----: | :--------: | :---------: | :------: |
| Cookie | JSESSIONID | Session ID  |    O     |

### 응답 (OK 200)
```
{
    "cred_def_id": "WgWxqztrNooG92RXvxSTWv:3:CL:14:revocable"
}
```

|     Key     |  Type  |       Description        | Nullable |
| :---------: | :----: | :----------------------: | :------: |
| cred_def_id | String | Credential Definition ID |    -     |

### 응답 (Internal Server Error 500)

|          Case           |       Description        |
| :---------------------: | :----------------------: |
| Login Info is not valid | Failed to get login info |

<br/>

## Credential 발급 요청 ✅
### GET /api/did/issuer/issue-credential
`(예시) /api/did/issuer/issue-credential`

<br/>

### 요청

| Header |    Key     | Description | Required |
| :----: | :--------: | :---------: | :------: |
| Cookie | JSESSIONID | Session ID  |    O     |

| Query | Type  |      Description      | Nullable |
| :---: | :---: | :-------------------: | :------: |
| temp  |  int  | User Body Temperature |    -     |

### 응답 (OK 200)
```
{
    "thread_id": "c8b51sd9-5291-4cbb-a3be-e1eedea7618f",
    "created_at": "2021-06-05 11:18:53.363883Z",
    "cred_ex_id": "20ea7368-5ce8-4e80-9050-b61ef5d80408"
}
```

|    Key     |  Type  |      Description       | Nullable |
| :--------: | :----: | :--------------------: | :------: |
| cred_ex_id | String | Credential Exchange ID |    -     |
| thread_id  | String |       Thread ID        |    -     |
| created_at | String |       Timestamp        |    -     |

### 응답 (Internal Server Error 500)

|          Case           |       Description        |
| :---------------------: | :----------------------: |
| Login Info is not valid | Failed to get login info |

<br/>

## Credential Revoke 요청 ✅
### GET /api/did/issuer/revoke-credential
`(예시) /api/did/issuer/revoke-credential`

<br/>

### 요청

|   Query   |  Type  |       Description        | Required |
| :-------: | :----: | :----------------------: | :------: |
| credEXId  | String |  Credential Exchange Id  |    O     |
| credRevId | String | Credential Revocation Id |    O     |
| revRegId  | String |  Revocation Registry Id  |    O     |

### 응답 (OK 200)
```
{
    "revoked": true
}
```

|   Key   |  Type   | Description | Nullable |
| :-----: | :-----: | :---------: | :------: |
| revoked | boolean | Is revoked? |    -     |

<br/>

# Verifier

## Proof 요청 ✅
### GET /api/did/verifier/prove
`(예시) /api/did/verifier/prove`

<br/>

### 요청

|    Query    |  Type  |    Description     | Required |
| :---------: | :----: | :----------------: | :------: |
|    alias    | String | Unique ID of Agent |    O     |
| moderatorId | String |    Moderator ID    |    O     |
|  location   | String | Moderator Location |    O     |

### 응답 (OK 200)
```
{
    "verified": true
}
```

|    Key    |  Type   |  Description  | Nullable |
| :-------: | :-----: | :-----------: | :------: |
| verfified | boolean | Is validated? |    -     |

### 응답 (Internal Server Error 500)

| Case  |     Description     |
| :---: | :-----------------: |
|   *   | Invalid query value |

<br/>

## Proof Verified 요청 ✅
### GET /api/did/verifier/verified
`(예시) /api/did/verifier/verified?presExId=20ea7368-5ce8-4e80-9050-b61ef5d80408`

<br/>

### 요청

|  Query  |  Type  |       Description        | Required |
| :-----: | :----: | :----------------------: | :------: |
| preExId | String | Presentation Exchange Id |    O     |

### 응답 (OK 200)
```
{
    "verified": true
}
```

|    Key    |  Type   |  Description  | Nullable |
| :-------: | :-----: | :-----------: | :------: |
| verfified | boolean | Is validated? |    -     |

<br/>
