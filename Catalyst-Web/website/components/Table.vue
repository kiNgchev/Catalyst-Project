<template>
    <div class="container">
        <table>
          <thead>
            <tr>
              <th>Image</th>
              <th>Title</th>
              <th>Description</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <PluginRow v-for="(item, index) in items" :key="index" :item="item" />
          </tbody>
        </table>
</div>
  </template>
  
  <script>
  
  export default {
    props: {
      items: Array
    }
  }
  </script>
  
<style>
.container {
    justify-content: center;
    align-items: center;
    text-align: center;
  }
</style>