import 'babel-polyfill'
import 'whatwg-fetch'
import React from 'react'
import {render} from 'react-dom'
import {Provider} from 'react-redux'
// 引入 react-hot-loader 提供的容器
import App from './containers/App'
import configureStore from './store/configureStore'
/*import 'todomvc-app-css/index.css'*/


const store = configureStore()


// http://blog.csdn.net/waiterwaiter/article/details/50267787
if (!Object.assign) {
    // 定义assign方法
    Object.defineProperty(Object, 'assign', {
        enumerable: false,
        configurable: true,
        writable: true,
        value: function(target) { // assign方法的第一个参数
            'use strict';
            // 第一个参数为空，则抛错
            if (target === undefined || target === null) {
                throw new TypeError('Cannot convert first argument to object');
            }

            var to = Object(target);
            // 遍历剩余所有参数
            for (var i = 1; i < arguments.length; i++) {
                var nextSource = arguments[i];
                // 参数为空，则跳过，继续下一个
                if (nextSource === undefined || nextSource === null) {
                    continue;
                }
                nextSource = Object(nextSource);

                // 获取改参数的所有key值，并遍历
                var keysArray = Object.keys(nextSource);
                for (var nextIndex = 0, len = keysArray.length; nextIndex < len; nextIndex++) {
                    var nextKey = keysArray[nextIndex];
                    var desc = Object.getOwnPropertyDescriptor(nextSource, nextKey);
                    // 如果不为空且可枚举，则直接浅拷贝赋值
                    if (desc !== undefined && desc.enumerable) {
                        to[nextKey] = nextSource[nextKey];
                    }
                }
            }
            return to;
        }
    });
}

render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('lazyLoadView')
)