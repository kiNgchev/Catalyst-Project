<template>
  <div>
    <CookieBanner/>
    <NuxtPage />
  </div>
</template>

<script>
export default {
  setup() {
    let { value: sessions } = useCookie('sessions');

    if (sessions) {
      sessions++;
      useCookie('sessions').value = sessions;
    } else {
      useCookie('sessions').value = 1;
    }
  }
}

</script>
