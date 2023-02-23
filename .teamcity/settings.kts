object TestSMBUploader : BuildType({
    name = "TestSMBUploader"

    params {
        password("SVC_ACCT_PASSWORD", "credentialsJSON:7204fafb-c850-43cd-9448-a550d8315821" )
    }

    steps {
        smbUpload {
            name = "Copy product folder to network"
            username = "svcacctuser"
            password = "%SVC_ACCT_PASSWORD%"
            sourcePath = "Test"
            targetUrl = """\\Network\MacBuilds"""
        }
    }
})
