<template>
  <a-layout id="components-layout-demo-top-side-2">
    <TheHeader/>
    <a-layout>
      <TheSider/>
      <a-layout style="padding: 0 24px 24px">
        <a-breadcrumb style="margin: 16px 0">
          <a-breadcrumb-item>Home</a-breadcrumb-item>
          <a-breadcrumb-item>List</a-breadcrumb-item>
          <a-breadcrumb-item>App</a-breadcrumb-item>
        </a-breadcrumb>
        <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
        >
          所有会员总数：{{count}}
        </a-layout-content>
      </a-layout>
    </a-layout>
  </a-layout>
</template>
<script>
import TheHeader from "@/components/TheHeader.vue";
import TheSider from "@/components/TheSider.vue";
import {defineComponent, ref} from "vue";
import axios from "axios";
import {notification} from "ant-design-vue";
export default defineComponent({
  components: {
    TheHeader,
    TheSider
  },
  setup() {
    const count = ref(0);

    // 没有带上token的话，会被拦截，前端报错401
    axios.get("/member/member/count").then((resp) => {
      let data = resp.data;
      if (data.success) {
        count.value = data.data;  
      } else {
        notification.error({ description: data.message });
      }
    })
    return {
      count,
    }
  }
})
</script>
<style scoped>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>