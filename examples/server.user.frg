syntax = "v1"

enum UserType {
    Portal = "portal";
    Trader = "trader";
}

type User {
    Sub              string            `json:"sub"`
    Username         string            `json:"username"`
    Type             UserType          `json:"type"`
    Name             string            `json:"name,omitempty"`
    Email            string            `json:"email,omitempty"`
    Role             string            `json:"role,omitempty"`
    EnabledMFA       bool              `json:"enabledMFA,omitempty"`
    Permissions      []string          `json:"permissions,omitempty"`
    APIPermissions   []string          `json:"apiPermissions,omitempty"`
    CreateDate       string            `json:"createDate,omitempty"`
    LastModifiedDate string            `json:"lastModifiedDate,omitempty"`
    Enabled          bool              `json:"enabled,omitempty"`
    UserStatus       string            `json:"userStatus,omitempty"`
    Attributes       map[string]string `json:"attributes,omitempty"`
}

type AssociateMFATokenRsp {
    QRCode string `json:"qrCode"`
}

type EnableMFAReq {
    UserCode string `json:"userCode" validate:"required"`
}

type ModifyPasswordReq {
    OldPassword string `json:"oldPassword" validate:"required"`
    NewPassword string `json:"newPassword" validate:"required"`
}

@attr(
	group: "user"
    auth: "Authorization"
	desc: "User related operations"
)
service {
    // summary: Profile of a user
    @handler getProfile
    get /api/v1/user/profile () returns (User)

    // summary: Associate MFA token
    @handler associateMFAToken
    get /api/v1/user/mfa_token () returns (AssociateMFATokenRsp)

    // summary: Enable MFA
    @handler enableMFA
    post /api/v1/user/enable_mfa (EnableMFAReq) returns ()

    // summary: Disable MFA
    @handler disableMFA
    post /api/v1/user/disable_mfa () returns ()

    // summary: Modify password
    @handler modifyPassword
    post /api/v1/user/modify_password (ModifyPasswordReq) returns ()
}
