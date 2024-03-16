<template>
  <div class="container">
    <table>
      <tbody>
        <PluginRow v-for="(item, index) in items" :key="index" :item="item" />
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  props: {
    items: Array,
  },
};
</script>

<style>
.container {
  justify-content: center;
  align-items: center;
  text-align: center;
}
</style>
