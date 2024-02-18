<template>
<div class="page">
  <Navbar/>
  <br>
  <div class="container">
    <div class="profile-info">
      <img src="/avatars/OpenStationDev.png" alt="" class="profile-logo">
      <p>OpenStationDev</p>
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
</style>
