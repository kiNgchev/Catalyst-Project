import { ComputedRef, MaybeRef } from "vue";
export type LayoutKey = string;
declare module "../../node_modules/.pnpm/nuxt@3.10.1_eslint@8.57.0_vite@5.1.1/node_modules/nuxt/dist/pages/runtime/composables" {
  interface PageMeta {
    layout?: MaybeRef<LayoutKey | false> | ComputedRef<LayoutKey | false>;
  }
}
