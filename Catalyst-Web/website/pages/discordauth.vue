<template>
  <h1>hi</h1>
</template>

<script>
export default {
  async asyncData({ $route }) {
    const code = $route.query.code || "";

    try {
      const response = await fetch("http://localhost:5000", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ code }),
      });
      const data = await response.json();
      console.log("Received data from server: ", data);

      return { responseData: data };
    } catch (error) {
      console.error("Error:", error);
      return { responseData: null };
    }
  },
};
</script>
