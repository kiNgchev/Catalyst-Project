<template>
<div class="page">
  <Navbar/>
  <br>
  <div class="container">
    <h3 class="profile-text">Profile</h3>
    <div class="profile-container" ref="profileContainer" @mouseleave="handleMouseLeave">
      <div class="profile-info">
        <button class="change-avatar-button" @click="changeAvatarEvent"><img :src="imageSource"  alt="" class="profile-logo"></button> <!--Change avatar button-->
        <input type="file" ref="fileInput" style="display: none" @change="handleFileChange">
        <!--<i class='bx bxs-edit-alt'></i>-->
        <button style="display: block" class="change-username-button" @click="changeUsernameEvent" v-if="usernameVisible"><p>ㅤ{{profileName}}</p></button>
        ㅤ<input type="text" v-model="usernameInputData"  v-if="usernameInputFieldVisible" placeholder="Enter new username" class="usernameInputField" @change="handleUsernameInputChange">
        ㅤ<button class="usernameInputButton" v-if="usernameInputButtonVisible" @click="changeUsernameEventButton">Change</button>
        </div>
    </div>
  </div>
</div>
</template>

<style>
.usernameInputButton {
  border-radius: 5px;
  cursor: pointer;
  background: none;
  font-size: 1rem;
  font-weight: 600;
  height: 35px;
}

.usernameInputField {
 border: none;
 border-radius: 5px;
 font-weight: 500;
 height: 35px;
 font-size: 1rem;
}

.change-username-button {
  border: none;
  background: none;
  font-size: 1.5rem;
  cursor: pointer;
}

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
  font-weight: 750;
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

<script>
export default {
  data() {
    return {
      imageSource: '',
      profileName: 'OpenStationDev',
      usernameVisible: true,
      usernameInputButtonVisible: false,
      usernameInputFieldVisible: false,
      usernameInputData: '',
    };
  },
  mounted() {
      this.imageSource = '/avatars/OpenStationDev.png';
  },
  methods: {
    handleMouseLeave(event) {
      if (event.clientY < 50) {
        this.$refs.profileContainer.style.transitionDuration = '0.1s'
      } else {
        this.$refs.profileContainer.style.transitionDuration = '1s';
      }
    },
    changeAvatarEvent() {
      this.$refs.fileInput.click();
    },
changeUsernameEvent() {
  this.usernameVisible = false;
  this.usernameInputButtonVisible = true;
  this.usernameInputFieldVisible = true;
},
changeUsernameEventButton() {
  if (this.usernameInputData.length < 3) {} else {
    this.profileName = this.usernameInputData;
    this.usernameVisible = true;
    this.usernameInputButtonVisible = false;
    this.usernameInputFieldVisible = false;
  }
},
    handleFileChange(event) {
      const file = event.target.files[0];
      if (!file) return;

      const reader = new FileReader();
      reader.onload = (e) => {
        this.imageSource = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }
}
</script>

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
