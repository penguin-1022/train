<template>
  <a-layout-header class="header">
    <div class="logo" />
    <div style="float: right; color: white;">
      您好：{{member.mobile}} &nbsp;&nbsp;
<!--      使用router-link搭配to来跳转页面，相当于a标签搭配href-->
      <router-link to="/login">
        退出登录
      </router-link>
    </div>
    <a-menu
        v-model:selectedKeys="selectedKeys"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/welcome">
        <router-link to="/welcome">
          <coffee-outlined/> &nbsp; 欢迎
        </router-link>
      </a-menu-item>
      <a-menu-item key="/passenger">
        <router-link to="/passenger">
          <user-outlined/> &nbsp; 乘车人管理
        </router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script>
import {defineComponent, ref, watch} from "vue";
import store from "@/store";
import router from "@/router";
export default defineComponent({
  name: "TheHeader",
  setup() {
    let member = store.state.member;
    const selectedKeys = ref([]);
    // newValue是页面上最新的路由
    watch(() => router.currentRoute.value.path, (newValue) => {
      console.log('watch', newValue);
      selectedKeys.value = [];
      selectedKeys.value.push(newValue);
    }, {immediate: true});
    return {
      member,
      selectedKeys
    }
  }
})
</script>

<style>

</style>