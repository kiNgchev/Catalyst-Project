<template>
  <div class="">
    <div class="navbar-container">
      <div class="navbar">
        <ul>
          <li><h1>ㅤㅤCatalyst</h1></li>
          <li><button @click="navigateTo('/')">Home</button></li>
          <li><button onclick="window.location.href='https://discord.com/api/oauth2/authorize?client_id=1207361111062609951&permissions=0&scope=bot'">Invite</button></li>
          <li><button @click="navigateTo('/Dashboard')">Dashboard</button></li>
          <li class="profile-btn"><button @click="navigateTo('/Profile')">Profile</button></li>
        </ul>
      </div>
    </div>
    <div class="content-container">
      <div class="developers-container">
        <h3>TEAM</h3>
        <h1>Developers of project</h1>
        <br><img src="/avatars/OpenStationDev.png" alt="OpenStation" class="developers-picture">
        <h2>OpenStation(KYBEX11) - Frontend Developer</h2>
        <img src="/avatars/kiNgchev.gif" alt="kiNgchev" class="developers-picture">
        <h2>kiNgchev - Founder, Backend Developer</h2>
      </div>
    </div>
  </div>
</template>


<style>
* {
  user-select: none;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

body {
  margin: 0;
  padding: 0;
  background-color: #141417;
}

.navbar-container {
  display: flex;
  justify-content: center;
}

.developers-picture {
  width: 100px;
  border-radius: 20px;
  height: 100px;
}

.content-container {
  justify-content: center;
  align-items: center;
  color: white;
  text-align: center;
}

.navbar {
  box-shadow: 0 0 5px white;
  width: 90%;
  height: 50px;
  border-radius: 8px;
  margin: 10px auto;
  display: flex;
  align-items: center;
}

ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  height: 100%;
  display: flex;
  align-items: center;
  width: 100%;
  text-align: center;
}

li {
  margin-right: auto;
}

li button {
  display: block;
  color: white;
  text-align: center;
  background: none;
  border: none;
  padding: 16px;
  text-decoration: none;
  border-radius: 25px;
  margin: 0 10px;
  font-size: 1.2rem;
  cursor: pointer;
  font-weight: 600;
}

li h1 {
  color: white;
  font-size: 1.5rem;
  margin-right: auto;
  font-weight: 800;
}

.profile-btn {
  margin-left: auto;
}

.profile-btn a {
  background: none;
}
</style>
