SystemJS.config({
  paths: {
    "js/": "js/"
  },
  browserConfig: {
    "baseURL": "/",
    "paths": {
      "npm:": "jspm_packages/npm/",
      "github:": "jspm_packages/github/"
    }
  },
  nodeConfig: {
    "paths": {
      "npm:": "build/jspm/static/jspm_packages/npm/",
      "github:": "build/jspm/static/jspm_packages/github/"
    }
  },
  devConfig: {
    "map": {
      "plugin-babel": "npm:systemjs-plugin-babel@0.0.12",
      "babel-plugin-transform-react-jsx": "npm:babel-plugin-transform-react-jsx@6.8.0",
      "core-js": "npm:core-js@2.4.1"
    },
    "packages": {
      "npm:babel-plugin-transform-react-jsx@6.8.0": {
        "map": {
          "babel-runtime": "npm:babel-runtime@6.11.6",
          "babel-helper-builder-react-jsx": "npm:babel-helper-builder-react-jsx@6.9.0",
          "babel-plugin-syntax-jsx": "npm:babel-plugin-syntax-jsx@6.8.0"
        }
      },
      "npm:babel-runtime@6.11.6": {
        "map": {
          "core-js": "npm:core-js@2.4.1",
          "regenerator-runtime": "npm:regenerator-runtime@0.9.5"
        }
      },
      "npm:babel-helper-builder-react-jsx@6.9.0": {
        "map": {
          "babel-runtime": "npm:babel-runtime@6.11.6",
          "babel-types": "npm:babel-types@6.11.1",
          "esutils": "npm:esutils@2.0.2",
          "lodash": "npm:lodash@4.14.1"
        }
      },
      "npm:babel-plugin-syntax-jsx@6.8.0": {
        "map": {
          "babel-runtime": "npm:babel-runtime@6.11.6"
        }
      },
      "npm:babel-types@6.11.1": {
        "map": {
          "babel-runtime": "npm:babel-runtime@6.11.6",
          "esutils": "npm:esutils@2.0.2",
          "lodash": "npm:lodash@4.14.1",
          "babel-traverse": "npm:babel-traverse@6.12.0",
          "to-fast-properties": "npm:to-fast-properties@1.0.2"
        }
      },
      "npm:babel-traverse@6.12.0": {
        "map": {
          "babel-runtime": "npm:babel-runtime@6.11.6",
          "babel-types": "npm:babel-types@6.11.1",
          "lodash": "npm:lodash@4.14.1",
          "babel-messages": "npm:babel-messages@6.8.0",
          "babel-code-frame": "npm:babel-code-frame@6.11.0",
          "invariant": "npm:invariant@2.2.1",
          "babylon": "npm:babylon@6.8.4",
          "globals": "npm:globals@8.18.0",
          "debug": "npm:debug@2.2.0"
        }
      },
      "npm:babel-messages@6.8.0": {
        "map": {
          "babel-runtime": "npm:babel-runtime@6.11.6"
        }
      },
      "npm:babel-code-frame@6.11.0": {
        "map": {
          "babel-runtime": "npm:babel-runtime@6.11.6",
          "esutils": "npm:esutils@2.0.2",
          "chalk": "npm:chalk@1.1.3",
          "js-tokens": "npm:js-tokens@2.0.0"
        }
      },
      "npm:babylon@6.8.4": {
        "map": {
          "babel-runtime": "npm:babel-runtime@6.11.6"
        }
      },
      "npm:invariant@2.2.1": {
        "map": {
          "loose-envify": "npm:loose-envify@1.2.0"
        }
      },
      "npm:debug@2.2.0": {
        "map": {
          "ms": "npm:ms@0.7.1"
        }
      },
      "npm:chalk@1.1.3": {
        "map": {
          "ansi-styles": "npm:ansi-styles@2.2.1",
          "has-ansi": "npm:has-ansi@2.0.0",
          "escape-string-regexp": "npm:escape-string-regexp@1.0.5",
          "supports-color": "npm:supports-color@2.0.0",
          "strip-ansi": "npm:strip-ansi@3.0.1"
        }
      },
      "npm:has-ansi@2.0.0": {
        "map": {
          "ansi-regex": "npm:ansi-regex@2.0.0"
        }
      },
      "npm:strip-ansi@3.0.1": {
        "map": {
          "ansi-regex": "npm:ansi-regex@2.0.0"
        }
      }
    }
  },
  transpiler: "plugin-babel",
  packages: {
    "js": {
      "main": "app.jsx",
      "format": "esm",
      "meta": {
        "*.js": {
          "loader": "plugin-babel"
        },
        "*.jsx": {
          "babelOptions": {
            "plugins": [
              "babel-plugin-transform-react-jsx"
            ]
          }
        }
      }
    }
  },
  map: {
    "paho-mqtt": "github:eclipse/paho.mqtt.javascript@1.0.2/src/mqttws31.js"
  }
});

SystemJS.config({
  packageConfigPaths: [
    "npm:@*/*.json",
    "npm:*.json",
    "github:*/*.json"
  ],
  map: {
    "assert": "github:jspm/nodelibs-assert@0.2.0-alpha",
    "buffer": "github:jspm/nodelibs-buffer@0.2.0-alpha",
    "child_process": "github:jspm/nodelibs-child_process@0.2.0-alpha",
    "constants": "github:jspm/nodelibs-constants@0.2.0-alpha",
    "crypto": "github:jspm/nodelibs-crypto@0.2.0-alpha",
    "domain": "github:jspm/nodelibs-domain@0.2.0-alpha",
    "eclipse/paho.mqtt.javascript": "github:eclipse/paho.mqtt.javascript@1.0.2",
    "events": "github:jspm/nodelibs-events@0.2.0-alpha",
    "fs": "github:jspm/nodelibs-fs@0.2.0-alpha",
    "http": "github:jspm/nodelibs-http@0.2.0-alpha",
    "https": "github:jspm/nodelibs-https@0.2.0-alpha",
    "node-uuid": "npm:node-uuid@1.4.7",
    "os": "github:jspm/nodelibs-os@0.2.0-alpha",
    "path": "github:jspm/nodelibs-path@0.2.0-alpha",
    "process": "github:jspm/nodelibs-process@0.2.0-alpha",
    "react": "npm:react@15.3.0",
    "react-dom": "npm:react-dom@15.3.0",
    "stream": "github:jspm/nodelibs-stream@0.2.0-alpha",
    "string_decoder": "github:jspm/nodelibs-string_decoder@0.2.0-alpha",
    "url": "github:jspm/nodelibs-url@0.2.0-alpha",
    "util": "github:jspm/nodelibs-util@0.2.0-alpha",
    "vm": "github:jspm/nodelibs-vm@0.2.0-alpha",
    "zlib": "github:jspm/nodelibs-zlib@0.2.0-alpha"
  },
  packages: {
    "github:jspm/nodelibs-crypto@0.2.0-alpha": {
      "map": {
        "crypto-browserify": "npm:crypto-browserify@3.11.0"
      }
    },
    "npm:crypto-browserify@3.11.0": {
      "map": {
        "create-hmac": "npm:create-hmac@1.1.4",
        "randombytes": "npm:randombytes@2.0.3",
        "create-hash": "npm:create-hash@1.1.2",
        "pbkdf2": "npm:pbkdf2@3.0.4",
        "inherits": "npm:inherits@2.0.1",
        "public-encrypt": "npm:public-encrypt@4.0.0",
        "diffie-hellman": "npm:diffie-hellman@5.0.2",
        "browserify-cipher": "npm:browserify-cipher@1.0.0",
        "create-ecdh": "npm:create-ecdh@4.0.0",
        "browserify-sign": "npm:browserify-sign@4.0.0"
      }
    },
    "npm:create-hmac@1.1.4": {
      "map": {
        "create-hash": "npm:create-hash@1.1.2",
        "inherits": "npm:inherits@2.0.1"
      }
    },
    "npm:create-hash@1.1.2": {
      "map": {
        "inherits": "npm:inherits@2.0.1",
        "sha.js": "npm:sha.js@2.4.5",
        "cipher-base": "npm:cipher-base@1.0.2",
        "ripemd160": "npm:ripemd160@1.0.1"
      }
    },
    "github:jspm/nodelibs-os@0.2.0-alpha": {
      "map": {
        "os-browserify": "npm:os-browserify@0.2.1"
      }
    },
    "npm:pbkdf2@3.0.4": {
      "map": {
        "create-hmac": "npm:create-hmac@1.1.4"
      }
    },
    "npm:public-encrypt@4.0.0": {
      "map": {
        "create-hash": "npm:create-hash@1.1.2",
        "randombytes": "npm:randombytes@2.0.3",
        "parse-asn1": "npm:parse-asn1@5.0.0",
        "browserify-rsa": "npm:browserify-rsa@4.0.1",
        "bn.js": "npm:bn.js@4.11.5"
      }
    },
    "npm:diffie-hellman@5.0.2": {
      "map": {
        "randombytes": "npm:randombytes@2.0.3",
        "miller-rabin": "npm:miller-rabin@4.0.0",
        "bn.js": "npm:bn.js@4.11.5"
      }
    },
    "npm:browserify-sign@4.0.0": {
      "map": {
        "create-hash": "npm:create-hash@1.1.2",
        "create-hmac": "npm:create-hmac@1.1.4",
        "inherits": "npm:inherits@2.0.1",
        "parse-asn1": "npm:parse-asn1@5.0.0",
        "browserify-rsa": "npm:browserify-rsa@4.0.1",
        "bn.js": "npm:bn.js@4.11.5",
        "elliptic": "npm:elliptic@6.3.1"
      }
    },
    "npm:sha.js@2.4.5": {
      "map": {
        "inherits": "npm:inherits@2.0.1"
      }
    },
    "npm:cipher-base@1.0.2": {
      "map": {
        "inherits": "npm:inherits@2.0.1"
      }
    },
    "npm:parse-asn1@5.0.0": {
      "map": {
        "create-hash": "npm:create-hash@1.1.2",
        "pbkdf2": "npm:pbkdf2@3.0.4",
        "evp_bytestokey": "npm:evp_bytestokey@1.0.0",
        "browserify-aes": "npm:browserify-aes@1.0.6",
        "asn1.js": "npm:asn1.js@4.8.0"
      }
    },
    "npm:browserify-rsa@4.0.1": {
      "map": {
        "randombytes": "npm:randombytes@2.0.3",
        "bn.js": "npm:bn.js@4.11.5"
      }
    },
    "npm:create-ecdh@4.0.0": {
      "map": {
        "bn.js": "npm:bn.js@4.11.5",
        "elliptic": "npm:elliptic@6.3.1"
      }
    },
    "npm:miller-rabin@4.0.0": {
      "map": {
        "bn.js": "npm:bn.js@4.11.5",
        "brorand": "npm:brorand@1.0.5"
      }
    },
    "npm:browserify-cipher@1.0.0": {
      "map": {
        "evp_bytestokey": "npm:evp_bytestokey@1.0.0",
        "browserify-des": "npm:browserify-des@1.0.0",
        "browserify-aes": "npm:browserify-aes@1.0.6"
      }
    },
    "npm:evp_bytestokey@1.0.0": {
      "map": {
        "create-hash": "npm:create-hash@1.1.2"
      }
    },
    "npm:browserify-des@1.0.0": {
      "map": {
        "cipher-base": "npm:cipher-base@1.0.2",
        "inherits": "npm:inherits@2.0.1",
        "des.js": "npm:des.js@1.0.0"
      }
    },
    "npm:browserify-aes@1.0.6": {
      "map": {
        "cipher-base": "npm:cipher-base@1.0.2",
        "create-hash": "npm:create-hash@1.1.2",
        "evp_bytestokey": "npm:evp_bytestokey@1.0.0",
        "inherits": "npm:inherits@2.0.1",
        "buffer-xor": "npm:buffer-xor@1.0.3"
      }
    },
    "npm:elliptic@6.3.1": {
      "map": {
        "bn.js": "npm:bn.js@4.11.5",
        "inherits": "npm:inherits@2.0.1",
        "brorand": "npm:brorand@1.0.5",
        "hash.js": "npm:hash.js@1.0.3"
      }
    },
    "npm:asn1.js@4.8.0": {
      "map": {
        "bn.js": "npm:bn.js@4.11.5",
        "inherits": "npm:inherits@2.0.1",
        "minimalistic-assert": "npm:minimalistic-assert@1.0.0"
      }
    },
    "npm:des.js@1.0.0": {
      "map": {
        "inherits": "npm:inherits@2.0.1",
        "minimalistic-assert": "npm:minimalistic-assert@1.0.0"
      }
    },
    "npm:hash.js@1.0.3": {
      "map": {
        "inherits": "npm:inherits@2.0.1"
      }
    },
    "github:jspm/nodelibs-string_decoder@0.2.0-alpha": {
      "map": {
        "string_decoder-browserify": "npm:string_decoder@0.10.31"
      }
    },
    "github:jspm/nodelibs-stream@0.2.0-alpha": {
      "map": {
        "stream-browserify": "npm:stream-browserify@2.0.1"
      }
    },
    "github:jspm/nodelibs-buffer@0.2.0-alpha": {
      "map": {
        "buffer-browserify": "npm:buffer@4.7.1"
      }
    },
    "npm:stream-browserify@2.0.1": {
      "map": {
        "inherits": "npm:inherits@2.0.1",
        "readable-stream": "npm:readable-stream@2.1.4"
      }
    },
    "npm:readable-stream@2.1.4": {
      "map": {
        "inherits": "npm:inherits@2.0.1",
        "string_decoder": "npm:string_decoder@0.10.31",
        "isarray": "npm:isarray@1.0.0",
        "process-nextick-args": "npm:process-nextick-args@1.0.7",
        "util-deprecate": "npm:util-deprecate@1.0.2",
        "buffer-shims": "npm:buffer-shims@1.0.0",
        "core-util-is": "npm:core-util-is@1.0.2"
      }
    },
    "npm:buffer@4.7.1": {
      "map": {
        "base64-js": "npm:base64-js@1.1.2",
        "isarray": "npm:isarray@1.0.0",
        "ieee754": "npm:ieee754@1.1.6"
      }
    },
    "npm:react@15.3.0": {
      "map": {
        "loose-envify": "npm:loose-envify@1.2.0",
        "fbjs": "npm:fbjs@0.8.3",
        "object-assign": "npm:object-assign@4.1.0"
      }
    },
    "npm:fbjs@0.8.3": {
      "map": {
        "loose-envify": "npm:loose-envify@1.2.0",
        "object-assign": "npm:object-assign@4.1.0",
        "core-js": "npm:core-js@1.2.7",
        "immutable": "npm:immutable@3.8.1",
        "ua-parser-js": "npm:ua-parser-js@0.7.10",
        "isomorphic-fetch": "npm:isomorphic-fetch@2.2.1",
        "promise": "npm:promise@7.1.1"
      }
    },
    "npm:loose-envify@1.2.0": {
      "map": {
        "js-tokens": "npm:js-tokens@1.0.3"
      }
    },
    "npm:isomorphic-fetch@2.2.1": {
      "map": {
        "node-fetch": "npm:node-fetch@1.5.3",
        "whatwg-fetch": "npm:whatwg-fetch@1.0.0"
      }
    },
    "npm:promise@7.1.1": {
      "map": {
        "asap": "npm:asap@2.0.4"
      }
    },
    "npm:node-fetch@1.5.3": {
      "map": {
        "is-stream": "npm:is-stream@1.1.0",
        "encoding": "npm:encoding@0.1.12"
      }
    },
    "npm:encoding@0.1.12": {
      "map": {
        "iconv-lite": "npm:iconv-lite@0.4.13"
      }
    },
    "github:jspm/nodelibs-url@0.2.0-alpha": {
      "map": {
        "url-browserify": "npm:url@0.11.0"
      }
    },
    "github:jspm/nodelibs-http@0.2.0-alpha": {
      "map": {
        "http-browserify": "npm:stream-http@2.3.1"
      }
    },
    "github:jspm/nodelibs-domain@0.2.0-alpha": {
      "map": {
        "domain-browserify": "npm:domain-browser@1.1.7"
      }
    },
    "npm:url@0.11.0": {
      "map": {
        "querystring": "npm:querystring@0.2.0",
        "punycode": "npm:punycode@1.3.2"
      }
    },
    "npm:stream-http@2.3.1": {
      "map": {
        "builtin-status-codes": "npm:builtin-status-codes@2.0.0",
        "inherits": "npm:inherits@2.0.1",
        "to-arraybuffer": "npm:to-arraybuffer@1.0.1",
        "xtend": "npm:xtend@4.0.1",
        "readable-stream": "npm:readable-stream@2.1.4"
      }
    },
    "github:jspm/nodelibs-zlib@0.2.0-alpha": {
      "map": {
        "zlib-browserify": "npm:browserify-zlib@0.1.4"
      }
    },
    "npm:browserify-zlib@0.1.4": {
      "map": {
        "readable-stream": "npm:readable-stream@2.1.4",
        "pako": "npm:pako@0.2.9"
      }
    }
  }
});
