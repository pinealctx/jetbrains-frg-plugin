syntax = "v1"

enum RequestType {
    // Subscriptions
    Subscribe      = "subscribe";      // (SubscribeData, UpdateData/CommonResponseData)
    Unsubscribe    = "unsubscribe";    // (SubscribeData, CommonResponseData)
    UnsubscribeAll = "unsubscribeAll"; // (nil, CommonResponseData)

    // Requests
    HedgePlaceOrder = "hedgePlaceOrder"; // (OrderRequestData, ClOrdID)
    // For testing purposes
    ClientPlaceOrder      = "clientPlaceOrder";      // (OrderRequestData, ClOrdID)
    ClientCancelOrder     = "clientCancelOrder";     // (OrderCancelRequestData, ClOrdID)
    ClientReplaceOrder    = "clientReplaceOrder";    // (OrderReplaceRequestData, ClOrdID)
    ClientOrderStatus     = "clientOrderStatus";     // (OrderStatusRequestData, CommonResponseData)
    ClientOrderMassStatus = "clientOrderMassStatus"; // (nil, ClOrdID)
}

enum ResponseType {
    Update  = "update";  // response -> UpdateData
    Common  = "common";  // response -> CommonResponseData
    ClOrdID = "clOrdID"; // response -> ClOrdID
}

type Request {
    ID   string          `json:"id"`
    Type RequestType     `json:"type"`
    Data json.RawMessage `json:"data,omitempty"`
}

type Response {
    ID   string      `json:"id,omitempty"`
    Type ResponseType `json:"type"`
    Data interface{} `json:"data,omitempty"`
}

type CommonResponseData {
    Success bool   `json:"success"`
    Message string `json:"message,omitempty"`
}

enum UpdateType {
    Summary         = "summary";         // (nil, SummaryData)
    Quotes          = "quotes";          // ([]string, map[string]map[string]*Quote)
    TierQuotes      = "tierQuotes";      // ([]string, map[string]map[string]*Quote)
    SymbolPositions = "symbolPositions"; // ([]string, map[string]*SymbolPosition)
    Notification    = "notification";    // (nil, Notification)
    LPSummaries     = "lpSummaries";     // (nil, map[string]*AccountSummary)

    // Testing purposes
    INTLRawData = "intlRawData"; // (nil, RawFIXData)
    POINRawData = "poinRawData"; // (nil, RawFIXData)
}

type UpdateData {
    Type UpdateType  `json:"type"`
    Data interface{} `json:"data"`
}

type SubscribeData {
    Type   UpdateType      `json:"type"`
    Params json.RawMessage `json:"params,omitempty"`
}

type SummaryData {
    TotalPNL           *float64 `json:"totalPNL"`           // Intraday total PNL
    ClientPNL          *float64 `json:"clientPNL"`          // Intraday total PNL for clients
    HedgePNL           *float64 `json:"hedgePNL"`           // Intraday total PNL for hedges
    TotalClientAmount  *float64 `json:"totalClientAmount"`  // Intraday total amount for clients
    Equity             *float64 `json:"equity"`             // Real-time equity across all LPs
    WithdrawableAmount *float64 `json:"withdrawableAmount"` // Real-time withdrawable amount across all LPs
    MarginUtilization  *float64 `json:"marginUtilization"`  // Real-time margin utilization across all LPs
    MarginRequirement  *float64 `json:"marginRequirement"`  // Real-time margin requirement across all LPs
}

type Quote {
    Symbol    string       `json:"symbol"`
    AccountID string       `json:"accountID"`
    Time      tex.JsInt64  `json:"time"`
    Bids      []*RateQty   `json:"bids"`
    Asks      []*RateQty   `json:"asks"`
}

type RateQty {
    Rate float64 `json:"rate"`
    Qty  float64 `json:"qty"`
}

type SymbolPosition {
    Customer float64 `json:"customer"` // Position for customers
    Hedge    float64 `json:"hedge"`    // Position for hedges
}

enum NotificationLevel {
    Info    = "info";    // Informational notification
    Warning = "warning"; // Warning notification
    Error   = "error";   // Error notification
}

type Notification {
    ID      string            `json:"id"`      // Unique identifier for the notification
    Time    tex.JsInt64       `json:"time"`    // Timestamp of the notification
    Level   NotificationLevel `json:"level"`   // Level of the notification (info, warning, error)
    Message string            `json:"message"` // Content of the notification
}

type AccountSummary {
    Name        string             `json:"name"`        // Name of the liquidity provider
    Positions   []*PositionSummary `json:"positions"`   // List of positions for the liquidity provider
    NOP         float64            `json:"nop"`         // Net open position for the liquidity provider
    TotalMargin float64            `json:"totalMargin"` // Total margin for the liquidity provider
    Alerts      []string           `json:"alerts"`      // List of alerts for the liquidity provider
}

type PositionSummary {
    Symbol      string   `json:"symbol"`      // Symbol for the position
    Qty         float64  `json:"qty"`         // Quantity of the position
    Margin      *float64 `json:"margin"`      // Margin for the position
    IntradayPNL *float64 `json:"intradayPNL"` // Intraday PNL for the position
    Exposure    *float64 `json:"exposure"`    // Exposure for the position
}

enum OrderSide {
    Unknown = 0;
    Buy     = 1;
    Sell    = 2;
}

enum OrderTimeInForce {
    Day                = 0;
    GoodTillCancel     = 1;
    ImmediateOrCancel  = 3;
    GoodTillDate       = 4;
    FillOrKill         = 5;
    Unknown            = 999;
}

enum OrderType {
    Unknown          = 0;
    Market           = 1;
    Limit            = 2;
    Stop             = 3;
    StopLimit        = 4;
    PreviouslyQuoted = 13;
}

type OrderRequestData {
    Account       string           `json:"account"`                // Trading account identifier
    Symbol        string           `json:"symbol"`                 // Trading symbol (e.g., EURUSD)
    Type          OrderType        `json:"type"`                   // Order type (market, limit, etc.)
    Side          OrderSide        `json:"side"`                   // Buy or sell
    Qty           float64          `json:"qty"`                    // Order quantity
    Price         *float64         `json:"price,omitempty"`        // Order price (for limit orders)
    StopPrice     *float64         `json:"stopPrice,omitempty"`    // Stop price (for stop orders)
    TimeInForce   OrderTimeInForce `json:"timeInForce"`            // Time in force
    ExpireTime    *int64           `json:"expireTime,omitempty"`   // Expiration time (timestamp)
}

type OrderCancelRequestData {
    OrigClOrdID string    `json:"origClOrdID"`           // Original client order ID
    OrigOrderID string    `json:"origOrderID,omitempty"` // Original system order ID
    Account     string    `json:"account"`               // Trading account
    Symbol      string    `json:"symbol"`                // Trading symbol
    Side        OrderSide `json:"side"`                  // Order side
    Qty         float64   `json:"qty"`                   // Order quantity
}

type OrderReplaceRequestData {
    OrigClOrdID string           `json:"origClOrdID"`           // Original client order ID
    OrigOrderID string           `json:"origOrderID,omitempty"` // Original system order ID
    Account     string           `json:"account"`               // Trading account
    Symbol      string           `json:"symbol"`                // Trading symbol
    Side        OrderSide        `json:"side"`                  // Order side
    Type        OrderType        `json:"type"`                  // Order type
    Qty         float64          `json:"qty"`                   // Order quantity
    Price       *float64         `json:"price,omitempty"`       // Order price
    StopPrice   *float64         `json:"stopPrice,omitempty"`   // Stop price
    TimeInForce OrderTimeInForce `json:"timeInForce"`           // Time in force
    ExpireTime  *int64           `json:"expireTime,omitempty"`  // Expiration time (timestamp)
}

type OrderStatusRequestData {
    ClOrdID string `json:"clOrdID,omitempty"` // Client order ID
    OrderID string `json:"orderID,omitempty"` // System order ID
    Account string `json:"account"`           // Trading account
}

type RawFIXData {
    Direction int32       `json:"direction"`        // Direction of the FIX message (1 for incoming, 2 for outgoing)
    Raw       []byte      `json:"raw"`              // Raw FIX message bytes
    MsgType   string      `json:"msgType"`          // FIX message type
    Parsed    interface{} `json:"parsed,omitempty"` // Parsed message data
}

type ClOrdID {
    ClOrdID string `json:"clOrdID"` // Client order ID
}
