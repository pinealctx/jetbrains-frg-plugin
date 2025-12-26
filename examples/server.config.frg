syntax = "v1"

type Symbol {
    Symbol             string  `json:"symbol"`
    Base               string  `json:"base"`
    Quote              string  `json:"quote"`
    Min                float64 `json:"min"`
    Max                float64 `json:"max"`
    Step               float64 `json:"step"`
    Security           string  `json:"security"`
    PipsUnit           int32   `json:"pipsUnit"`
    ContractSize       float64 `json:"contractSize"`
    PricePrecision     int32   `json:"pricePrecision"`
    QuotedOrderMaxQty  float64 `json:"quotedOrderMaxQty"`
    PortalMaxOrderLots float64 `json:"portalMaxOrderLots"`
}

enum AccountType {
    Customer = "CUSTOMER";
    LP       = "LP";
}

type Account {
    AccountID   string      `json:"accountID"`
    AccountType AccountType `json:"accountType"`
    AccountName string      `json:"accountName,omitempty"`
    Tier        string      `json:"tier,omitempty"`
    IsXSyphon   bool        `json:"isXSyphon"`
    Enabled     bool        `json:"enabled"`
}

type OrderTypeConfig {
    OrderType    OrderType                `json:"orderType"`
    Disabled     bool                     `json:"disabled"`
    TimeInForces []OrderTimeInForceConfig `json:"timeInForces"`
}

type OrderTimeInForceConfig {
    TimeInForce OrderTimeInForce `json:"timeInForce"`
    Disabled    bool             `json:"disabled"`
    Attributes  []string         `json:"attributes,omitempty"`
}

@attr(
	group: "config"
    auth: "Authorization"
	desc: "Config related operations"
)
service {
    // summary: Get all symbols
    @handler getSymbols
    get /api/v1/config/symbols () returns ([]*Symbol)

    // summary: Get all accounts
    @handler getAccounts
    get /api/v1/config/accounts () returns ([]*Account)

    // summary: Get order type configurations
    @handler getOrderTypes
    get /api/v1/config/orderTypes () returns ([]*OrderTypeConfig)
}
