<template>
  <transition
    appear
    enter-active-class="animated-element-enter"
    leave-active-class="animated-element-leave"
  >
    <div class="">
      <Navbar />
      <br />

      <div class="bot-bio-container">
        <img
          src="/CatalystLogoPatent.png"
          class="bot-patent-logo"
          alt="Discord Bot powerfully logo"
          height="100"
          width="100"
        />
        <h1>ㅤCatalyst</h1>
        <div class="bot-description-container">
          <div class="br-container"><br /><br /><br /><br /><br /></div>
          <h2>Multipurpose Discord Bot.</h2>
          <h2>Full customizable.</h2>
          <h2>Absolutely free.</h2>
        </div>
        <br />
      </div>
      <h3 class="view-our-commands">VIEW OUR COMMANDS</h3>
      <div class="commands-pre-container">
        <marquee behavior="" direction="" scrollamount="20">
          <div class="pre-command-container">
            ㅤㅤㅤ
            <h1>/kick</h1>
            ㅤㅤㅤ
            <h1>/ban</h1>
            ㅤㅤㅤ
            <h1>/userinfo</h1>
            ㅤㅤㅤ
            <h1>/mute</h1>
            ㅤㅤㅤ
            <h1>/lock</h1>
            ㅤㅤㅤ
            <h1>/purge</h1>
            ㅤㅤㅤ
            <h1>/report</h1>
            ㅤㅤㅤ
            <h1>/warn</h1>
            ㅤㅤㅤ
            <h1>/warns</h1>
            ㅤㅤㅤ
            <h1>/avatar</h1>
            ㅤㅤㅤ
            <h1>/guildinfo</h1>
            ㅤㅤㅤ
            <h1>/channelinfo</h1>
            ㅤㅤㅤ
            <h1>/emojis</h1>
            ㅤㅤㅤ
            <h1>/roles</h1>
            ㅤㅤㅤ
            <h1>/ping</h1>
            ㅤㅤㅤ
            <h1>/config</h1>
            ㅤㅤㅤ
            <h1>/permissions</h1>
            ㅤㅤㅤ
            <h1>/list</h1>
            ㅤㅤㅤ
            <h1>/dashboard</h1>
            ㅤㅤㅤ
            <h1>/donate</h1>
            ㅤㅤㅤ
            <h1>/balance</h1>
            ㅤㅤㅤ
            <h1>Coming Soon...</h1>
          </div>
        </marquee>
      </div>
      <br /><br /><br />
      <div class="content-container">
        <div class="developers-container">
          <h3>TEAM</h3>
          <h1>Developers of project</h1>

          <div class="developer-profile-container">
            <br /><img
              src="/avatars/OpenStationDev.png"
              alt="OpenStation"
              class="developers-picture"
            />
            <br />
            <h2>ㅤㅤOpenStation(KYBEX11)ㅤ</h2>
            <p>- Owner, Frontend, Backend Developerㅤㅤ</p>
          </div>
          <br />
          <div class="developer-profile-container">
            <img
              src="/avatars/kiNgchev.gif"
              alt="kiNgchev"
              class="developers-picture"
            />
            <h2>ㅤㅤkiNgchev(KINGCHEV)ㅤ</h2>
            <p>- Founder, Owner, Backend Developerㅤㅤㅤ</p>
          </div>
          <br />
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import { gsap } from "gsap";
export default {
  mounted() {
    gsap.fromTo(this.$el, { opacity: 0 }, { opacity: 1, duration: 1 });
  },
};
</script>

<style>
.animated-element-enter,
.animated-element-leave-to {
  opacity: 0;
}
.bot-description-container {
  display: block;
  transform: translateX(-90%);
}
.bot-bio-container {
  color: white;
  display: flex;
  transform: translateX(35%);
}
.bot-bio-container h1 {
  font-size: 2.5rem;
}
.developer-profile-container p {
  font-size: 1.2rem;
  color: gray;
  font-weight: 800;
}

.developer-profile-container img {
  margin-right: 10px;
}

.developer-profile-container {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}

.view-our-commands {
  text-align: center;
}

.commands-pre-container {
  background: #17171a;
  color: white;
}

.pre-command-container {
  display: flex;
}

* {
  user-select: none;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
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
  border-radius: 50%;
  height: 100px;
}

.developers-container h3,
.view-our-commands {
  font-weight: 800;
  color: #b0adff;
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
<script setup>
useHead({
  link: [
    {
      rel: "stylesheet",
      href: "/PluginRow.css",
    },
  ],
});
</script>
