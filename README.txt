Short description:
SampleListView app demonstrates typical implementation of a ListView UI component of Android.

Salient features of app:
1. Implementation aims at smooth scrolling through list items.
2. Asynchronously fetch data from URL in JSON format, using Volley Network Lib
3. Load images from their respective URLs lazily using universal-image-loader 3rd party lib.
4. Refresh the list with both Refresh button and Pull to refresh.

External Libraries used:
1. Volley - A HTTP network utility library from Android used for all the networking tasks.We use this to get the JSON response from the specified URL.

2. Universal Image Loader - A 3rd party Image loader utility library.Optimised to download images from specific URL asynchronously.Lazy loading of image in ListView and caching are the other features.

3. GSON - A JSON parsing library from Google, used to serialization/deserialization JSON Objects to POJO and visa-versa