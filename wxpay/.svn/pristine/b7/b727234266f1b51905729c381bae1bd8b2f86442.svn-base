function main(context) {
    redissetvalue("openid",context.openid,"5");
    if (rediscontainKey("openid")){
        return {status:"success",openid:redisgetvalue("openid")};
    }else {
        return {status:"fail",openid:""};
    }
}