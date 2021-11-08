# Google-Search-Engine

This is a Java Spring Boot Application in which you send a Post API with a keyword and it returns the first 10 google search results using Google custome search api.

This application runs in port 8083 .

The post api implements basic authentications and Content-Type is application/json . 

The json body is as follow:

{
	"keyword":""
}


In the GoogleSearchEngineApp/src/main/resources/templates/Credentials.json file please set your username and password as this file acts as a dummy data storage.



