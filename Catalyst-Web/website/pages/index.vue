<template>
  <div class="">
    <Navbar/>
    <br/>
    <h3 class="view-our-commands">VIEW OUR COMMANDS</h3>
      <div class="commands-pre-container">
            <marquee behavior="" direction="" scrollamount="20">
              <div class="pre-command-container">
                ㅤㅤㅤ<h1>/kick</h1>
                ㅤㅤㅤ<h1>/ban</h1>
                ㅤㅤㅤ<h1>/userinfo</h1>
                ㅤㅤㅤ<h1>/mute</h1>
                ㅤㅤㅤ<h1>/lock</h1>
                ㅤㅤㅤ<h1>/purge</h1>
                ㅤㅤㅤ<h1>/report</h1>
                ㅤㅤㅤ<h1>/warn</h1>
                ㅤㅤㅤ<h1>/warns</h1>
                ㅤㅤㅤ<h1>/avatar</h1>
                ㅤㅤㅤ<h1>/guildinfo</h1>
                ㅤㅤㅤ<h1>/channelinfo</h1>
                ㅤㅤㅤ<h1>/emojis</h1>
                ㅤㅤㅤ<h1>/roles</h1>
                ㅤㅤㅤ<h1>/ping</h1>
                ㅤㅤㅤ<h1>/config</h1>
                ㅤㅤㅤ<h1>/permissions</h1>
                ㅤㅤㅤ<h1>/list</h1>
                ㅤㅤㅤ<h1>/dashboard</h1>
                ㅤㅤㅤ<h1>/donate</h1>
                ㅤㅤㅤ<h1>/balance</h1>
                ㅤㅤㅤ<h1>Coming Soon...</h1>
              </div>
            </marquee>
      </div>
      <br/><br><br>
    <div class="content-container">
      <div class="developers-container">
        <h3>TEAM</h3>
        <h1>Developers of project</h1>
        <br><img src="/avatars/OpenStationDev.png" alt="OpenStation" class="developers-picture">
        <h2>OpenStation(KYBEX11) - Owner, Frontend, Backend Developer</h2>
        <img src="/avatars/kiNgchev.gif" alt="kiNgchev" class="developers-picture">
        <h2>kiNgchev - Owner, Backend Developer</h2>
      </div>
    </div>
  </div>
</template>

<style>

.view-our-commands {
  text-align: center;
}

.commands-pre-container {
  background: #17171A;
  color: white;
}

.pre-command-container {
  display: flex;
}

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

.developers-container h3,
.view-our-commands {
  font-weight: 800;
  color: #B0ADFF;
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
