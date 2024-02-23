<template>
  <div v-if="!isClosed" class="cookie-banner">
    <p>The website using cookie 🍪</p>
    <button @click="closeBanner">Accept</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isClosed: false
    };
  },
  mounted() {
    const isBannerClosed  = useCookie('isCookieBannerClosed').value;
    if (isBannerClosed) {
      this.isClosed = true;
    }
  },
  methods: {
    closeBanner() {
      this.isClosed = true;
      useCookie('isCookieBannerClosed').value = true;
    }
  }
};
</script>

<style scoped>
.cookie-banner {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #1694FE;
  color: #fff;
  padding: 10px;
}

.cookie-banner p {
  font-size: 1.5rem;
  font-weight: 800
}

.cookie-banner button {
  border: none;
  background-color: #182434;
  color: white;
  border-radius: 10px;
  width: 130px;
  height: 70px;
  font-size: 1.35rem;
  font-weight: 600
}
</style>
