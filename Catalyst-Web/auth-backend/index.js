const http = require("http");
const unirest = require("unirest");

const server = http.createServer((req, res) => {
  if (req.method === "POST") {
    let body = "";

    req.on("data", (chunk) => {
      body += chunk.toString();

      let clientID = "949927873249148998";
      let redirect_uri = "http://localhost:3000/discordauth";
      let clientSecret = "jZ6nBQaQN8VgjR5xLa08Nd6_c4sxU-uS";
      let requestPayload = {
        // todo:проверить почему эта хуйня нечего не выводит
        redirect_uri,
        client_id: clientID,
        grant_type: "authorization_code",
        client_secret: clientSecret,
        code: body,
      };

      unirest
        .post("https://discord.com/api/oauth/token")
        .send(requestPayload)
        .headers({
          "Content-Type": "application/x-www-form-urlencoded",
          "User-Agent": "DiscordBot",
        })
        .then((data) => {
          console.log(data.body);
        });
    });
    req.on("end", () => {
      res.end("Data received and logged in the console.");
    });
  } else {
    res.end("Server is expecting only POST requests.");
  }
});

const PORT = 5000;

server.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
