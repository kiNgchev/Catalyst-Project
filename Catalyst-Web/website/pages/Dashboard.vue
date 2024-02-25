<template>
  <div class="">
  <Navbar/>
  <div class="" v-if="auth">

  </div>
  <div class="else-view" v-else>
    <h1>Please. Login first!</h1>
    <button onclick="window.location.href='https://discord.com/oauth2/authorize?client_id=949927873249148998&response_type=code&redirect_uri=http%3A%2F%2Flocalhost%3A3000%2Fdiscordauth&scope=identify'">Login by Discord</button>
  </div>
</div>
</template>

<script>
const auth = useCookie('auth').value;
</script>

<style>
.else-view h1 {
  text-align: center;
  color: white;
}
</style>
