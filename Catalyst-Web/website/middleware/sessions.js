export default function ({ req, res }) {
  if (process.server) {
    let count = parseInt(req.req.cookies.sessions) || 0;
    count++;
    res.setHeader("Set-Cookie", `sessions=${count}`);
  }
}
