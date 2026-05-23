<script lang="ts">
  import { CircleQuestionMark } from "@lucide/svelte";
  import {
    getAppTvBanner,
    type AppInfo,
  } from "tauri-plugin-android-utils-api";

  type Props = {
    app: AppInfo;
  };

  let { app }: Props = $props();

  let banner = $state<string | null>(null);
  let failed = $state(false);

  $effect(() => {
    let cancelled = false;
    (async () => {
      try {
        const result = await getAppTvBanner(app.packageName);
        if (cancelled) return;

        if (result?.data) {
          const mime = result.mimeType ?? "image/png";
          banner = `data:${mime};base64,${result.data}`;
        } else {
          failed = true;
        }
      } catch {
        if (!cancelled) failed = true;
      }
    })();
    return () => {
      cancelled = true;
    };
  });
</script>

<button class="flex flex-col items-center gap-2">
  <div
    class="flex h-17.25 w-17.25 items-center justify-center overflow-hidden bg-[#C0C0C0]"
  >
    {#if banner && !failed}
      <img
        src={banner}
        alt={app.appName}
        class="h-full w-full object-cover"
        onerror={() => (failed = true)}
      />
    {:else}
      <CircleQuestionMark class="p-5" />
    {/if}
  </div>
  <span class="line-clamp-1 text-xs text-white">
    {app.appName}
  </span>
</button>