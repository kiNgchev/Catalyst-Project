<template>
  <div class="plugin-view-a-container">
    <transition
      appear
      enter-active-class="animated-element-enter"
      leave-active-class="animated-element-leave"
    >
      <div class="item-container">
        <tr>
          <td>
            <img
              class="img-plugins-row-view"
              :src="item.image"
              alt="Plugin image"
            />
          </td>
          <br />
          <td>
            <h1>{{ item.title }}</h1>
          </td>
          <br />
          <td>
            <h2>{{ item.description }}</h2>
          </td>
          <br />
          <td><button @click="handleButtonClick">Order Now</button></td>
        </tr>
      </div>
    </transition>
  </div>
</template>

<script>
import { gsap } from "gsap";
export default {
  mounted() {
    gsap.fromTo(this.$el, { opacity: 0 }, { opacity: 1, duration: 1 });
  },
  props: {
    item: Object,
  },
  methods: {
    handleButtonClick() {},
  },
};
</script>

<style>
.animated-element-enter,
.animated-element-leave-to {
  opacity: 0;
}
.item-container {
  display: flex;
}

td button {
  border: none;
  width: 100px;
  cursor: pointer;
  border-radius: 10px;
  height: 50px;
  font-size: 1rem;
  font-weight: 700;
  color: #765d69;
  background-color: #fcd0ba;
  transition-duration: 1s;
}

td button:hover {
  transform: scale(1.1);
  color: #fcd0ba;
  background-color: #765d69;
}

td h1,
td h2 {
  color: white;
}
.plugin-view-a-container {
  justify-content: center;
  align-items: center;
  text-align: center;
}

.img-plugins-row-view {
  height: 200px;
  width: 200px;
  border-radius: 10px;
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
