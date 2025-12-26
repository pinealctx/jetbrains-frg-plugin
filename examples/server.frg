syntax = "v1"

info(
    title: "xSyphon BI Backend Server"
	desc: "xSyphon BI Backend Server API"
	gomod: "xsyphon.com/bi/backend"
	version: "v1.0.0"
)

import "server.auth.frg"
import "server.config.frg"
import "server.user.frg"
import "server.dashboard.frg"
import "server.ws.frg"
import "server.misc.frg"

@externDefs {
    name:"tex.JsInt64", swaggerType:"string", importPath:"github.com/pinealctx/neptune/tex"
    name:"json.RawMessage", swaggerType:"interface{}", importPath:"encoding/json"
}

type SwaggerSecurity {
	ApiKey string `header:"Authorization"`
}