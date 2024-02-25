<template>
  <div class="">
  <Navbar/>
  <div class="" v-if="auth">
    <div class="sidebar"></div>
  </div>
  <div class="else-view" v-else>
    <h1>Please. Login first!ㅤㅤㅤ</h1>
    <button class="login-button" onclick="window.location.href='https://discord.com/oauth2/authorize?client_id=949927873249148998&response_type=code&redirect_uri=http%3A%2F%2Flocalhost%3A3000%2Fdiscordauth&scope=identify'">Login by Discord</button>
  </div>
</div>
</template>

<script setup>
useCookie('auth').value = true;
const auth = useCookie('auth').value;
</script>

<style>
.login-button {
  border: none;
  background-color: #182434;
  color: white;
  border-radius: 10px;
  width: 130px;
  height: 60px;
  font-size: 1rem;
  font-weight: 800;
  cursor: pointer;
}
.else-view {
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.else-view h1 {
  text-align: center;
  color: white;
}
</style>
