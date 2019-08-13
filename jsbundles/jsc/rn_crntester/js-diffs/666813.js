__d(function(g,r,i,a,m,e,d){'use strict';var t=(function(t){function n(){var t,l;r(d[1])(this,n);for(var u=arguments.length,o=new Array(u),s=0;s<u;s++)o[s]=arguments[s];return(l=r(d[2])(this,(t=r(d[3])(n)).call.apply(t,[this].concat(o)))).state={curText:'<No Event>',prevText:'<No Event>',prev2Text:'<No Event>',prev3Text:'<No Event>'},l.updateText=function(t){l.setState(function(n){return{curText:t,prevText:n.curText,prev2Text:n.prevText,prev3Text:n.prev2Text}})},l}return r(d[0])(n,t),r(d[4])(n,[{key:"render",value:function(){var t=this;return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).TextInput,{autoCapitalize:"none",placeholder:"Enter text to see events",autoCorrect:!1,multiline:!0,onFocus:function(){return t.updateText('onFocus')},onBlur:function(){return t.updateText('onBlur')},onChange:function(n){return t.updateText('onChange text: '+n.nativeEvent.text)},onContentSizeChange:function(n){return t.updateText('onContentSizeChange size: '+JSON.stringify(n.nativeEvent.contentSize))},onEndEditing:function(n){return t.updateText('onEndEditing text: '+n.nativeEvent.text)},onSubmitEditing:function(n){return t.updateText('onSubmitEditing text: '+n.nativeEvent.text)},onKeyPress:function(n){return t.updateText('onKeyPress key: '+n.nativeEvent.key)},style:p.singleLine}),r(d[5]).createElement(r(d[6]).Text,{style:p.eventLabel},this.state.curText,'\n',"(prev: ",this.state.prevText,")",'\n',"(prev2: ",this.state.prev2Text,")",'\n',"(prev3: ",this.state.prev3Text,")"))}}]),n})(r(d[5]).Component),n=(function(t){function n(t){var l;return r(d[1])(this,n),(l=r(d[2])(this,r(d[3])(n).call(this,t))).state={text:''},l}return r(d[0])(n,t),r(d[4])(n,[{key:"render",value:function(){var t=this,n=20-this.state.text.length,l=n>5?'blue':'red';return r(d[5]).createElement(r(d[6]).View,{style:p.rewriteContainer},r(d[5]).createElement(r(d[6]).TextInput,{multiline:!1,maxLength:20,onChangeText:function(n){n=n.replace(/ /g,'_'),t.setState({text:n})},style:p.default,value:this.state.text}),r(d[5]).createElement(r(d[6]).Text,{style:[p.remainder,{color:l}]},n))}}]),n})(r(d[5]).Component),l=(function(t){function n(t){var l;return r(d[1])(this,n),(l=r(d[2])(this,r(d[3])(n).call(this,t))).state={text:'Hello #World'},l}return r(d[0])(n,t),r(d[4])(n,[{key:"render",value:function(){for(var t,n,l=this,u=/\s+/,o=this.state.text,s=[];o&&(u.lastIndex=0,null!==(t=u.exec(o)));)n=t.index,0===t[0].length&&(n=1),s.push(o.substr(0,n)),s.push(t[0]),n+=t[0].length,o=o.slice(n);return s.push(o),s=s.map(function(t){return/^#/.test(t)?r(d[5]).createElement(r(d[6]).Text,{key:t,style:p.hashtag},t):t}),r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).TextInput,{multiline:!0,style:p.multiline,onChangeText:function(t){l.setState({text:t})}},r(d[5]).createElement(r(d[6]).Text,null,s)))}}]),n})(r(d[5]).Component),u=(function(t){function n(){var t,l;r(d[1])(this,n);for(var u=arguments.length,o=new Array(u),s=0;s<u;s++)o[s]=arguments[s];return(l=r(d[2])(this,(t=r(d[3])(n)).call.apply(t,[this].concat(o)))).focusNextField=function(t){l.refs[t].focus()},l}return r(d[0])(n,t),r(d[4])(n,[{key:"render",value:function(){var t=this;return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).TextInput,{ref:"1",style:p.singleLine,placeholder:"blurOnSubmit = false",returnKeyType:"next",blurOnSubmit:!1,onSubmitEditing:function(){return t.focusNextField('2')}}),r(d[5]).createElement(r(d[6]).TextInput,{ref:"2",style:p.singleLine,keyboardType:"email-address",placeholder:"blurOnSubmit = false",returnKeyType:"next",blurOnSubmit:!1,onSubmitEditing:function(){return t.focusNextField('3')}}),r(d[5]).createElement(r(d[6]).TextInput,{ref:"3",style:p.singleLine,keyboardType:"url",placeholder:"blurOnSubmit = false",returnKeyType:"next",blurOnSubmit:!1,onSubmitEditing:function(){return t.focusNextField('4')}}),r(d[5]).createElement(r(d[6]).TextInput,{ref:"4",style:p.singleLine,keyboardType:"numeric",placeholder:"blurOnSubmit = false",blurOnSubmit:!1,onSubmitEditing:function(){return t.focusNextField('5')}}),r(d[5]).createElement(r(d[6]).TextInput,{ref:"5",style:p.singleLine,keyboardType:"numbers-and-punctuation",placeholder:"blurOnSubmit = true",returnKeyType:"done"}))}}]),n})(r(d[5]).Component),o=(function(t){function n(t){var l;return r(d[1])(this,n),(l=r(d[2])(this,r(d[3])(n).call(this,t))).state={hasPadding:!1},l}return r(d[0])(n,t),r(d[4])(n,[{key:"render",value:function(){var t=this;return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).TextInput,{style:this.state.hasPadding?{padding:0}:null}),r(d[5]).createElement(r(d[6]).Text,{onPress:function(){return t.setState({hasPadding:!t.state.hasPadding})}},"Toggle padding"))}}]),n})(r(d[5]).Component),s=(function(t){function n(t){var l;return r(d[1])(this,n),(l=r(d[2])(this,r(d[3])(n).call(this,t))).state={selection:{start:0,end:0},value:t.value},l}return r(d[0])(n,t),r(d[4])(n,[{key:"onSelectionChange",value:function(t){var n=t.nativeEvent.selection;this.setState({selection:n})}},{key:"getRandomPosition",value:function(){var t=this.state.value.length;return Math.round(Math.random()*t)}},{key:"select",value:function(t,n){this._textInput.focus(),this.setState({selection:{start:t,end:n}})}},{key:"selectRandom",value:function(){var t=[this.getRandomPosition(),this.getRandomPosition()].sort();this.select.apply(this,r(d[7])(t))}},{key:"placeAt",value:function(t){this.select(t,t)}},{key:"placeAtRandom",value:function(){this.placeAt(this.getRandomPosition())}},{key:"render",value:function(){var t=this,n=this.state.value.length;return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).TextInput,{multiline:this.props.multiline,onChangeText:function(n){return t.setState({value:n})},onSelectionChange:this.onSelectionChange.bind(this),ref:function(n){return t._textInput=n},selection:this.state.selection,style:this.props.style,value:this.state.value}),r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).Text,null,"selection = ",JSON.stringify(this.state.selection)),r(d[5]).createElement(r(d[6]).Text,{onPress:this.placeAt.bind(this,0)},"Place at Start (0, 0)"),r(d[5]).createElement(r(d[6]).Text,{onPress:this.placeAt.bind(this,n)},"Place at End (",n,", ",n,")"),r(d[5]).createElement(r(d[6]).Text,{onPress:this.placeAtRandom.bind(this)},"Place at Random"),r(d[5]).createElement(r(d[6]).Text,{onPress:this.select.bind(this,0,n)},"Select All"),r(d[5]).createElement(r(d[6]).Text,{onPress:this.selectRandom.bind(this)},"Select Random")))}}]),n})(r(d[5]).Component),c=(function(t){function n(t){var l;return r(d[1])(this,n),(l=r(d[2])(this,r(d[3])(n).call(this,t))).state={width:100,multiline:!0,text:'',contentSize:{width:0,height:0}},l}return r(d[0])(n,t),r(d[4])(n,[{key:"UNSAFE_componentWillReceiveProps",value:function(t){this.setState({multiline:t.multiline})}},{key:"render",value:function(){var t=this,n=this.props,l=n.style,u=(n.multiline,r(d[8])(n,["style","multiline"]));return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).Text,null,"Width:"),r(d[5]).createElement(r(d[6]).Slider,{value:100,minimumValue:0,maximumValue:100,step:10,onValueChange:function(n){return t.setState({width:n})}}),r(d[5]).createElement(r(d[6]).Text,null,"Multiline:"),r(d[5]).createElement(r(d[6]).Switch,{value:this.state.multiline,onValueChange:function(n){return t.setState({multiline:n})}}),r(d[5]).createElement(r(d[6]).Text,null,"TextInput:"),r(d[5]).createElement(r(d[6]).TextInput,r(d[9])({multiline:this.state.multiline,style:[l,{width:this.state.width+'%'}],onChangeText:function(n){return t.setState({text:n})},onContentSizeChange:function(n){return t.setState({contentSize:n.nativeEvent.contentSize})}},u)),r(d[5]).createElement(r(d[6]).Text,null,"Plain text value representation:"),r(d[5]).createElement(r(d[6]).Text,null,this.state.text),r(d[5]).createElement(r(d[6]).Text,null,"Content Size: ",JSON.stringify(this.state.contentSize)))}}]),n})(r(d[5]).Component),p=r(d[6]).StyleSheet.create({multiline:{height:60,fontSize:16},eventLabel:{margin:3,fontSize:12},singleLine:{fontSize:16},singleLineWithHeightTextInput:{height:30},hashtag:{color:'blue',fontWeight:'bold'}});e.title='<TextInput>',e.description='Single and multi-line text inputs.',e.examples=[{title:'Auto-focus',render:function(){return r(d[5]).createElement(r(d[6]).TextInput,{autoFocus:!0,multiline:!0,style:p.input,accessibilityLabel:"I am the accessibility label for text input"})}},{title:"Live Re-Write (<sp>  ->  '_')",render:function(){return r(d[5]).createElement(n,null)}},{title:'Auto-capitalize',render:function(){var t=['none','sentences','words','characters'].map(function(t){return r(d[5]).createElement(r(d[6]).TextInput,{key:t,autoCapitalize:t,placeholder:'autoCapitalize: '+t,style:p.singleLine})});return r(d[5]).createElement(r(d[6]).View,null,t)}},{title:'Auto-correct',render:function(){return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).TextInput,{autoCorrect:!0,placeholder:"This has autoCorrect",style:p.singleLine}),r(d[5]).createElement(r(d[6]).TextInput,{autoCorrect:!1,placeholder:"This does not have autoCorrect",style:p.singleLine}))}},{title:'Keyboard types',render:function(){var t=['default','email-address','numeric','phone-pad'].map(function(t){return r(d[5]).createElement(r(d[6]).TextInput,{key:t,keyboardType:t,placeholder:'keyboardType: '+t,style:p.singleLine})});return r(d[5]).createElement(r(d[6]).View,null,t)}},{title:'Blur on submit',render:function(){return r(d[5]).createElement(u,null)}},{title:'Event handling',render:function(){return r(d[5]).createElement(t,null)}},{title:'Colors and text inputs',render:function(){return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).TextInput,{style:[p.singleLine],defaultValue:"Default color text"}),r(d[5]).createElement(r(d[6]).TextInput,{style:[p.singleLine,{color:'green'}],defaultValue:"Green Text"}),r(d[5]).createElement(r(d[6]).TextInput,{placeholder:"Default placeholder text color",style:p.singleLine}),r(d[5]).createElement(r(d[6]).TextInput,{placeholder:"Red placeholder text color",placeholderTextColor:"red",style:p.singleLine}),r(d[5]).createElement(r(d[6]).TextInput,{placeholder:"Default underline color",style:p.singleLine}),r(d[5]).createElement(r(d[6]).TextInput,{placeholder:"Blue underline color",style:p.singleLine,underlineColorAndroid:"blue"}),r(d[5]).createElement(r(d[6]).TextInput,{defaultValue:"Same BackgroundColor as View ",style:[p.singleLine,{backgroundColor:'rgba(100, 100, 100, 0.3)'}]},r(d[5]).createElement(r(d[6]).Text,{style:{backgroundColor:'rgba(100, 100, 100, 0.3)'}},"Darker backgroundColor")),r(d[5]).createElement(r(d[6]).TextInput,{defaultValue:"Highlight Color is red",selectionColor:'red',style:p.singleLine}))}},{title:'Text input, themes and heights',render:function(){return r(d[5]).createElement(r(d[6]).TextInput,{placeholder:"If you set height, beware of padding set from themes",style:[p.singleLineWithHeightTextInput]})}},{title:'fontFamily, fontWeight and fontStyle',render:function(){return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).TextInput,{style:[p.singleLine,{fontFamily:'sans-serif'}],placeholder:"Custom fonts like Sans-Serif are supported"}),r(d[5]).createElement(r(d[6]).TextInput,{style:[p.singleLine,{fontFamily:'sans-serif',fontWeight:'bold'}],placeholder:"Sans-Serif bold"}),r(d[5]).createElement(r(d[6]).TextInput,{style:[p.singleLine,{fontFamily:'sans-serif',fontStyle:'italic'}],placeholder:"Sans-Serif italic"}),r(d[5]).createElement(r(d[6]).TextInput,{style:[p.singleLine,{fontFamily:'serif'}],placeholder:"Serif"}))}},{title:'letterSpacing',render:function(){return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).TextInput,{style:[p.singleLine,{letterSpacing:0}],placeholder:"letterSpacing = 0"}),r(d[5]).createElement(r(d[6]).TextInput,{style:[p.singleLine,{letterSpacing:2}],placeholder:"letterSpacing = 2"}),r(d[5]).createElement(r(d[6]).TextInput,{style:[p.singleLine,{letterSpacing:9}],placeholder:"letterSpacing = 9"}),r(d[5]).createElement(r(d[6]).TextInput,{style:[p.singleLine,{letterSpacing:-1}],placeholder:"letterSpacing = -1"}))}},{title:'Passwords',render:function(){return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).TextInput,{defaultValue:"iloveturtles",secureTextEntry:!0,style:p.singleLine}),r(d[5]).createElement(r(d[6]).TextInput,{secureTextEntry:!0,style:[p.singleLine,{color:'red'}],placeholder:"color is supported too",placeholderTextColor:"red"}))}},{title:'Editable',render:function(){return r(d[5]).createElement(r(d[6]).TextInput,{defaultValue:"Can't touch this! (>'-')> ^(' - ')^ <('-'<) (>'-')> ^(' - ')^",editable:!1,style:p.singleLine})}},{title:'Multiline',render:function(){return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).TextInput,{autoCorrect:!0,placeholder:"multiline, aligned top-left",placeholderTextColor:"red",multiline:!0,style:[p.multiline,{textAlign:'left',textAlignVertical:'top'}]}),r(d[5]).createElement(r(d[6]).TextInput,{autoCorrect:!0,placeholder:"multiline, aligned center",placeholderTextColor:"green",multiline:!0,style:[p.multiline,{textAlign:'center',textAlignVertical:'center'}]}),r(d[5]).createElement(r(d[6]).TextInput,{autoCorrect:!0,multiline:!0,style:[p.multiline,{color:'blue'},{textAlign:'right',textAlignVertical:'bottom'}]},r(d[5]).createElement(r(d[6]).Text,{style:p.multiline},"multiline with children, aligned bottom-right")))}},{title:'Fixed number of lines',platform:'android',render:function(){return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).TextInput,{numberOfLines:2,multiline:!0,placeholder:"Two line input"}),r(d[5]).createElement(r(d[6]).TextInput,{numberOfLines:5,multiline:!0,placeholder:"Five line input"}))}},{title:'Auto-expanding',render:function(){return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(c,{enablesReturnKeyAutomatically:!0,returnKeyType:"done",multiline:!0,style:{maxHeight:400,minHeight:20,backgroundColor:'#eeeeee'}},"generic generic generic",r(d[5]).createElement(r(d[6]).Text,{style:{fontSize:6,color:'red'}},"small small small small small small"),r(d[5]).createElement(r(d[6]).Text,null,"regular regular"),r(d[5]).createElement(r(d[6]).Text,{style:{fontSize:30,color:'green'}},"huge huge huge huge huge"),"generic generic generic"))}},{title:'Attributed text',render:function(){return r(d[5]).createElement(l,null)}},{title:'Return key',render:function(){var t=['none','go','search','send','done','previous','next'].map(function(t){return r(d[5]).createElement(r(d[6]).TextInput,{key:t,returnKeyType:t,placeholder:'returnKeyType: '+t,style:p.singleLine})}),n=['Compile','React Native'].map(function(t){return r(d[5]).createElement(r(d[6]).TextInput,{key:t,returnKeyLabel:t,placeholder:'returnKeyLabel: '+t,style:p.singleLine})});return r(d[5]).createElement(r(d[6]).View,null,t,n)}},{title:'Inline Images',render:function(){return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).TextInput,{inlineImageLeft:"ic_menu_black_24dp",placeholder:"This has drawableLeft set",style:p.singleLine}),r(d[5]).createElement(r(d[6]).TextInput,{inlineImageLeft:"ic_menu_black_24dp",inlineImagePadding:30,placeholder:"This has drawableLeft and drawablePadding set",style:p.singleLine}),r(d[5]).createElement(r(d[6]).TextInput,{placeholder:"This does not have drawable props set",style:p.singleLine}))}},{title:'Toggle Default Padding',render:function(){return r(d[5]).createElement(o,null)}},{title:'Text selection & cursor placement',render:function(){return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(s,{style:p.default,value:"text selection can be changed"}),r(d[5]).createElement(s,{multiline:!0,style:p.multiline,value:'multiline text selection\ncan also be changed'}))}}]},666813,[614,616,617,620,621,514,516,636,640,622]);