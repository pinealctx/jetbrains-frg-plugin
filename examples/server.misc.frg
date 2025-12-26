syntax = "v1"

@attr(
	group: "misc"
    auth: "Authorization"
	desc: "Miscellaneous related operations"
)
service {
    // summary: Get miscellaneous market data
    @handler getMiscMarket
    get /api/v1/misc/market () returns (interface{})
}
