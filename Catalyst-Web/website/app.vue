<template>
  <div>
    <CookieBanner/>
    <NuxtPage />
  </div>
</template>

<script>
let sessions = useCookie('sessions').value;

if (sessions) {
  sessions++;
  useCookie('sessions').value = sessions;
} else {
  useCookie('sessions').value = 1;
}

</script>
