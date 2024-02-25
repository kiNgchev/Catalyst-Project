export default defineNuxtConfig({
  devtools: { enabled: false },
  router: {
    middleware: "sessions",
  },
});
