package main

import (
    "fmt"
    "net/http"
    "net/url"
    "os"

    "golang.org/x/oauth2"

)

var (
    discordOauthConfig = &oauth2.Config{
        ClientID:     os.Getenv("DISCORD_CLIENT_ID"),
        ClientSecret: os.Getenv("DISCORD_CLIENT_SECRET"),
        Endpoint: oauth2.Endpoint{
            AuthURL:  "https://discord.com/api/oauth2/authorize",
            TokenURL: "https://discord.com/api/oauth2/token",
        },
        RedirectURL: "",
        Scopes:      []string{"identify"},
    )
)

func handleDiscordAuth(w http.ResponseWriter, r *http.Request) {
    code := r.URL.Query().Get("code")
    if code == "" {
        http.Error(w, "Missing code parameter", http.StatusBadRequest)
        return
    }

    token, err := discordOauthConfig.Exchange(r.Context(), code)
    if err != nil {
        http.Error(w, fmt.Sprintf("Failed to exchange code: %v", err), http.StatusInternalServerError)
        return
    }

    http.Redirect(w, r, "http://localhost:5000?username="+url.QueryEscape("someusername")+"&id=someuserid", http.StatusTemporaryRedirect)
}

func main() {
    http.HandleFunc("/discord-auth", handleDiscordAuth)

    http.ListenAndServe(":5000", nil)
}
