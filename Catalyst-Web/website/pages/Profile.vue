<template>
<div class="page">
  <Navbar/>
  <br>
  <div class="container">
    <h3 class="profile-text">Profile</h3>
    <div class="profile-container">
      <div class="profile-info">
        <button class="change-avatar-button"><img src="/avatars/OpenStationDev.png" alt="" class="profile-logo"></button> <!--Change avatar button-->
        <!--<i class='bx bxs-edit-alt'></i>-->
        <p>ㅤOpenStationDev</p>
      </div>
    </div>
  </div>
</div>
</template>

<style>
.profile-text {
  color: #B0ADFF;
  font-weight: 1000;
}

.profile-container {
  background-color: #f0f0f0;
  display: flex;
  height: 200px;
  width: 500px;
  border-radius: 15px;
  justify-content: center;
  text-align: center;
  margin: auto;
  align-items: center;
  box-shadow: 0px 0px 15px white;
  transition: transform 0.3s ease;
}

.profile-container:hover {
  transform: scale(1.05);
  transition: transform 0.3s ease;
}

.profile-info p {
  color: black;
}

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
