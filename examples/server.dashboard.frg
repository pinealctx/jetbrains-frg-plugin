syntax = "v1"

enum LayoutType {
    Grid = "grid";
}

type Dashboard {
    ID           tex.JsInt64 `json:"id"`
    Name         string      `json:"name"`
    Description  string      `json:"description,omitempty"`
    LayoutType   LayoutType  `json:"layoutType"`
    DisplayOrder int32       `json:"displayOrder"`
    Widgets      []*Widget   `json:"widgets"`
    CreatedAt    int64       `json:"createdAt"`
    UpdatedAt    int64       `json:"updatedAt"`
}

type Widget {
    ID           int32       `json:"id"`
    MetaID       string      `json:"metaID"`
    Config       interface{} `json:"config"`
    DisplayOrder int32       `json:"displayOrder"`
}

type Int64 {
    ID tex.JsInt64 `json:"id" query:"id"`
}

type UpdateDashboardBasicReq {
    ID           tex.JsInt64 `json:"id"`
    Name         string      `json:"name"`
    Description  string      `json:"description,omitempty"`
    LayoutType   LayoutType  `json:"layoutType"`
    DisplayOrder int32       `json:"displayOrder"`
}

type WidgetMeta {
    ID            string      `json:"id"`
    Name          string      `json:"name"`
    DefaultConfig interface{} `json:"defaultConfig,omitempty"`
    CreatedAt     int64       `json:"createdAt"`
    UpdatedAt     int64       `json:"updatedAt"`
}

type UpdateWidgetReq {
    DashboardID tex.JsInt64 `json:"dashboardID"`
    Widget      *Widget     `json:"widget"`
}

@attr(
	group: "dashboard"
    auth: "Authorization"
	desc: "Dashboard related operations"
)
service {
    // summary: Get dashboards
    @handler getDashboards
    get /api/v1/dashboard/list () returns ([]*Dashboard)

    // summary: Get dashboard by ID
    @handler getDashboardByID
    get /api/v1/dashboard/get (Int64) returns (Dashboard)

    // summary: Add dashboard
    @handler addDashboard
    post /api/v1/dashboard/add (Dashboard) returns (Dashboard)

    // summary: Update dashboard
    @handler updateDashboard
    post /api/v1/dashboard/update (Dashboard) returns (Dashboard)

    // summary: Update dashboard basic information
    @handler updateDashboardBasic
    post /api/v1/dashboard/update_basic (UpdateDashboardBasicReq) returns (Dashboard)

    // summary: Remove dashboard
    @handler removeDashboard
    post /api/v1/dashboard/remove (Int64) returns ()

    // summary: Update widget
    @handler updateWidget
    post /api/v1/dashboard/update_widget (UpdateWidgetReq) returns (Widget)
}
