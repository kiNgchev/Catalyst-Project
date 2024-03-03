package main

import (
    "encoding/json"
    "fmt"
    "net/http"
    "net/url"

)

const (
    clientID     = "949927873249148998"
    clientSecret = "750SOfAZtf36ZC69I_ESfQnr43oTpWqD"
    redirectURI  = "http://localhost:3000/discordauth"
)

func main() {
    http.HandleFunc("/getUserData", func(w http.ResponseWriter, r *http.Request) {
        code := r.URL.Query().Get("code")

        token, err := exchangeCodeForToken(code)
        if err != nil {
            fmt.Println("Error exchanging code for token:", err)
            http.Error(w, "Internal server error", http.StatusInternalServerError)
            return
        }

        userData, err := getUserData(token.AccessToken)
        if err != nil {
            fmt.Println("Error getting user data:", err)
            http.Error(w, "Internal server error", http.StatusInternalServerError)
            return
        }

        w.Header().Set("Content-Type", "application/json")
        json.NewEncoder(w).Encode(userData)
    })

    http.ListenAndServe(":8080", nil)
}

func exchangeCodeForToken(code string) (*OAuthToken, error) {
    data := url.Values{}
    data.Set("client_id", clientID)
    data.Set("client_secret", clientSecret)
    data.Set("grant_type", "authorization_code")
    data.Set("code", code)
    data.Set("redirect_uri", redirectURI)

    resp, err := http.PostForm("https://discord.com/api/oauth2/token", data)
    if err != nil {
        return nil, err
    }
    defer resp.Body.Close()

    var token OAuthToken
    err = json.NewDecoder(resp.Body).Decode(&token)
    if err != nil {
        return nil, err
    }

    return &token, nil
}

func getUserData(accessToken string) (map[string]interface{}, error) {
    req, err := http.NewRequest("GET", "https://discord.com/api/users/@me", nil)
    if err != nil {
        return nil, err
    }
    req.Header.Set("Authorization", "Bearer "+accessToken)

    client := http.Client{}
    resp, err := client.Do(req)
    if err != nil {
        return nil, err
    }
    defer resp.Body.Close()

    var userData map[string]interface{}
    err = json.NewDecoder(resp.Body).Decode(&userData)
    if err != nil {
        return nil, err
    }

    return userData, nil
}

type OAuthToken struct {
    AccessToken string `json:"access_token"`
    TokenType   string `json:"token_type"`
    ExpiresIn   int    `json:"expires_in"`
    Scope       string `json:"scope"`
}
