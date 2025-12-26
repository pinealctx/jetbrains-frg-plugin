syntax = "v1"

type SignInReq {
    Username string `json:"username" validate:"required"`
    Password string `json:"password" validate:"required"`
}

type SignInRsp {
    Success         bool        `json:"success"`
    Message         string      `json:"message,omitempty"`
    NeedMFA         bool        `json:"needMfa,omitempty"`
    NeedNewPassword bool        `json:"needNewPassword,omitempty"`
    Session         string      `json:"session,omitempty"`
    AuthResult      *AuthResult `json:"authResult,omitempty"`
}

type AuthResult {
    AccessToken  string `json:"accessToken,omitempty"`
    RefreshToken string `json:"refreshToken,omitempty"`
    TokenType    string `json:"tokenType,omitempty"`
    ExpiresIn    int32  `json:"expiresIn,omitempty"`
}

type RefreshAccessTokenReq {
    RefreshToken string `json:"refreshToken" validate:"required"`
    Username     string `json:"username"`
}

type ForceResetPasswordReq {
    Username    string `json:"username" validate:"required"`
    Session     string `json:"session" validate:"required"`
    NewPassword string `json:"newPassword" validate:"required"`
}

type VerifyMFAReq {
    Username string `json:"username" validate:"required"`
    Session  string `json:"session" validate:"required"`
    MFACode  string `json:"mfaCode" validate:"required"`
}

type OAuthTokenReq {
    Code  string `json:"code" validate:"required"`
}

type String {
    Value string `json:"value"`
}

@attr(
	group: "auth"
	desc: "Authentication related operations"
)
service {
    // summary: Health check
    @handler healthCheck
    get /api/v1/health_check () returns ()

    // summary: Get AWS SSO URL
    @handler getAWSAuthorizeURL
    get /api/v1/auth/aws_sso_authorize_url () returns (String)

	// summary: Obtain AWS SSO token
	@handler obtainAWSSSOToken
	post /api/v1/auth/aws_sso_token (OAuthTokenReq) returns (SignInRsp)

    // summary: Sign in
    @handler signIn
	post /api/v1/auth/sign_in (SignInReq) returns (SignInRsp)

    // summary: Sign out
    @handler signOut
	post /api/v1/auth/sign_out () returns ()

    // summary: Force change password
    @handler forceChangePassword
	post /api/v1/auth/force_reset_password (ForceResetPasswordReq) returns (SignInRsp)

    // summary: Software token MFA
    @handler verifyMFA
	post /api/v1/auth/verify_mfa (VerifyMFAReq) returns (SignInRsp)

	// summary: Refresh access token
	@handler refreshAccessToken
	post /api/v1/auth/refresh (RefreshAccessTokenReq) returns (SignInRsp)
}
