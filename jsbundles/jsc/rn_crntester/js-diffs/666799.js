__d(function(g,r,i,a,m,e,d){'use strict';var t=(function(t){function o(t){var s;return r(d[1])(this,o),(s=r(d[2])(this,r(d[3])(o).call(this,t))).cancelled=!1,s.state={status:'',a:1,b:2},s}return r(d[0])(o,t),r(d[4])(o,[{key:"setCookie",value:function(t){var s=this,o=this.state,n=o.a,l=o.b;fetch("https://"+t+"/cookies/set?a="+n+"&b="+l).then(function(t){s.setStatus("Cookies a="+n+", b="+l+" set"),s.refreshWebview()}),this.setState({status:'Setting cookies...',a:n+1,b:l+2})}},{key:"getCookies",value:function(t){var s=this;fetch("https://"+t+"/cookies").then(function(t){return t.json()}).then(function(t){s.setStatus("Got cookies "+JSON.stringify(t.cookies)+" from server"),s.refreshWebview()}),this.setStatus('Getting cookies...')}},{key:"clearCookies",value:function(){var t=this;r(d[5]).clearCookies(function(s){t.setStatus('Cookies cleared, had cookies='+s.toString()),t.refreshWebview()})}},{key:"refreshWebview",value:function(){this.refs.webview.reload()}},{key:"setStatus",value:function(t){this.setState({status:t})}},{key:"render",value:function(){return r(d[6]).createElement(r(d[7]).View,null,r(d[6]).createElement(r(d[7]).TouchableHighlight,{style:s.wrapper,onPress:this.setCookie.bind(this,'httpbin.org')},r(d[6]).createElement(r(d[7]).View,{style:s.button},r(d[6]).createElement(r(d[7]).Text,null,"Set cookie"))),r(d[6]).createElement(r(d[7]).TouchableHighlight,{style:s.wrapper,onPress:this.setCookie.bind(this,'eu.httpbin.org')},r(d[6]).createElement(r(d[7]).View,{style:s.button},r(d[6]).createElement(r(d[7]).Text,null,"Set cookie (EU)"))),r(d[6]).createElement(r(d[7]).TouchableHighlight,{style:s.wrapper,onPress:this.getCookies.bind(this,'httpbin.org')},r(d[6]).createElement(r(d[7]).View,{style:s.button},r(d[6]).createElement(r(d[7]).Text,null,"Get cookies"))),r(d[6]).createElement(r(d[7]).TouchableHighlight,{style:s.wrapper,onPress:this.getCookies.bind(this,'eu.httpbin.org')},r(d[6]).createElement(r(d[7]).View,{style:s.button},r(d[6]).createElement(r(d[7]).Text,null,"Get cookies (EU)"))),r(d[6]).createElement(r(d[7]).TouchableHighlight,{style:s.wrapper,onPress:this.clearCookies.bind(this)},r(d[6]).createElement(r(d[7]).View,{style:s.button},r(d[6]).createElement(r(d[7]).Text,null,"Clear cookies"))),r(d[6]).createElement(r(d[7]).Text,null,this.state.status),r(d[6]).createElement(r(d[7]).TouchableHighlight,{style:s.wrapper,onPress:this.refreshWebview.bind(this)},r(d[6]).createElement(r(d[7]).View,{style:s.button},r(d[6]).createElement(r(d[7]).Text,null,"Refresh Webview"))),r(d[6]).createElement(r(d[7]).WebView,{ref:"webview",source:{uri:'http://httpbin.org/cookies'},style:{height:100}}))}}]),o})(r(d[6]).Component),s=r(d[7]).StyleSheet.create({wrapper:{borderRadius:5,marginBottom:5},button:{backgroundColor:'#eeeeee',padding:8}});m.exports=t},666799,[614,616,617,620,621,190,514,516]);