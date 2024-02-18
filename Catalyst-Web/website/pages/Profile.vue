<template>
<div class="page">
  <Navbar/>
  <br>
  <div class="container">
    <div class="profile-info">
      <button class="change-avatar-button"><img src="/avatars/OpenStationDev.png" alt="" class="profile-logo"></button> <!--Change avatar button-->
      <!--<i class='bx bxs-edit-alt'></i>-->
      <p>ㅤOpenStationDev</p>
    </div>
  </div>
</div>
</template>

<style>
.container {
  align-content: center;
  text-align: center;
  justify-content: center;
  height: 100vh;
}

.profile-info {
  display: flex;
  align-items: center;
  text-align: center;
  justify-content: center;
  color: white;
  font-weight: 600;
  font-size: 1.5rem;
}

.profile-logo {
  height: 100px;
  width: 100px;
  border-radius: 100%;
  margin-right: 20px;
}

.change-avatar-button {
  border-radius: 100px;
  width: 100px;
  height: 100px;
  background: none;
  border: none;
  cursor: pointer;
}
</style>

<script setup>
useHead({
  link: [
    {
      rel: "stylesheet",
      href: "https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
    }
  ]
})
</script>
